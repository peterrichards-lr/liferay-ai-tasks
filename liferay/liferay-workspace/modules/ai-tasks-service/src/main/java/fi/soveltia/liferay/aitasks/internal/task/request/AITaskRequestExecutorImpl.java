
package fi.soveltia.liferay.aitasks.internal.task.request;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.MapUtil;

import fi.soveltia.liferay.aitasks.internal.task.node.condition.AITaskNodeConditionEvaluationResponse;
import fi.soveltia.liferay.aitasks.internal.task.node.condition.AITaskNodeConditionEvaluator;
import fi.soveltia.liferay.aitasks.model.AITask;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.Configuration;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.Edge;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.Node;
import fi.soveltia.liferay.aitasks.spi.task.node.AITaskNode;
import fi.soveltia.liferay.aitasks.task.context.AITaskContext;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeResponse;
import fi.soveltia.liferay.aitasks.task.request.AITaskRequest;
import fi.soveltia.liferay.aitasks.task.request.AITaskRequestExecutor;
import fi.soveltia.liferay.aitasks.task.response.AITaskResponse;
import fi.soveltia.liferay.aitasks.task.response.AITaskResponseBuilder;
import fi.soveltia.liferay.aitasks.task.response.AITaskResponseBuilderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(service = AITaskRequestExecutor.class)
public class AITaskRequestExecutorImpl implements AITaskRequestExecutor {

	public AITaskResponse execute(AITaskRequest aiTaskRequest) {
		AITaskResponseBuilder aiTaskResponseBuilder =
			_aiTaskResponseBuilderFactory.builder();

		AITask aiTask = aiTaskRequest.getAITask();

		if (!aiTask.getEnabled()) {
			aiTaskResponseBuilder.addOutput(
				"error",
				"Oh dear, the requested task could not be found or is not " +
					"enabled");

			return aiTaskResponseBuilder.build();
		}

		Configuration configuration = Configuration.toDTO(
			aiTask.getConfigurationJSON());

		Node node = _getStartNode(configuration);

		if (node == null) {
			aiTaskResponseBuilder.addOutput(
				"error",
				StringBundler.concat(
					"Oh dear, the start node ", configuration.getStartNodeId(),
					" could not be found! Please check the task configuration ",
					"and try again."));

			return aiTaskResponseBuilder.build();
		}

		long startTime = System.currentTimeMillis();

		try {
			if (_evaluateCondition(
					aiTaskRequest.getAITaskContext(), aiTaskResponseBuilder,
					configuration.getDebug(), aiTaskRequest.getInput(), node)) {

				_executeTaskNode(
					aiTaskRequest.getAITaskContext(), aiTaskResponseBuilder,
					configuration.getDebug(), configuration.getEdges(),
					aiTaskRequest.getInput(), node, configuration.getNodes());
			}
		}
		catch (Exception exception) {
			_log.error(exception);

			aiTaskResponseBuilder.addDebugInfo(
				HashMapBuilder.<String, Object>put(
					"exception", exception.toString()
				).build(),
				node.getId());

			aiTaskResponseBuilder.addOutput(
				"error",
				StringBundler.concat(
					"Oh dear, something went wrong! Please check the task ",
					"configuration and try again.\n\nReason:\n", "```json\n",
					exception.getMessage(), "\n```\n"));
		}

		if (configuration.getDebug()) {
			aiTaskResponseBuilder.took(
				(System.currentTimeMillis() - startTime) + "ms");
		}

		return aiTaskResponseBuilder.build();
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, AITaskNode.class, "ai.task.node.type");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private void _addDebugInfo(
		AITaskNodeResponse aiTaskNodeResponse,
		AITaskResponseBuilder aiTaskResponseBuilder, String id) {

		if (aiTaskNodeResponse == null) {
			return;
		}

		aiTaskResponseBuilder.addDebugInfo(
			aiTaskNodeResponse.getDebugInfo(), id);
	}

	private void _addExecutionTime(
		AITaskNodeResponse aiTaskNodeResponse, long startTime) {

		if (aiTaskNodeResponse == null) {
			return;
		}

		Map<String, Object> debugInfo = aiTaskNodeResponse.getDebugInfo();

		debugInfo.put(
			"executionTime", (System.currentTimeMillis() - startTime) + "ms");
	}

	private void _addOutput(
		AITaskResponseBuilder aiTaskResponseBuilder,
		AITaskNodeResponse aiTaskNodeResponse) {

		if (aiTaskNodeResponse == null) {
			return;
		}

		MapUtil.isNotEmptyForEach(
			aiTaskNodeResponse.getOutput(),
			(k, v) -> aiTaskResponseBuilder.addOutput(k, v));
	}

	private boolean _evaluateCondition(
		AITaskContext aiTaskContext,
		AITaskResponseBuilder aiTaskResponseBuilder, boolean debug,
		Map<String, Object> input, Node node) {

		if (node.getCondition() == null) {
			return true;
		}

		AITaskNodeConditionEvaluator aiTaskNodeConditionEvaluator =
			new AITaskNodeConditionEvaluator(aiTaskContext, debug, input);

		AITaskNodeConditionEvaluationResponse
			aiTaskNodeConditionEvaluationResponse =
				aiTaskNodeConditionEvaluator.evaluate(node.getCondition());

		if (debug) {
			aiTaskResponseBuilder.addDebugInfo(
				HashMapBuilder.<String, Object>put(
					"condition",
					aiTaskNodeConditionEvaluationResponse.getDebugInfo()
				).build(),
				node.getId());
		}

		return aiTaskNodeConditionEvaluationResponse.isValid();
	}

	private void _executeTaskNode(
		AITaskContext aiTaskContext,
		AITaskResponseBuilder aiTaskResponseBuilder, boolean debug,
		Edge[] edges, Map<String, Object> input, Node node, Node[] nodes) {

		AITaskNode aiTaskNode = _serviceTrackerMap.getService(node.getType());

		if (aiTaskNode == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"AI task node of type " + node.getType() +
						" could not be found");
			}

			return;
		}

		long startTime = System.currentTimeMillis();

		AITaskNodeResponse aiTaskNodeResponse = aiTaskNode.execute(
			aiTaskContext, debug, node.getId(), input,
			_jsonFactory.createJSONObject((Map)node.getParameters()));

		if (debug) {
			_addDebugInfo(
				aiTaskNodeResponse, aiTaskResponseBuilder, node.getId());
			_addExecutionTime(aiTaskNodeResponse, startTime);
		}

		_addOutput(aiTaskResponseBuilder, aiTaskNodeResponse);

		List<Node> nextNodes = _getNextNodes(edges, node.getId(), nodes);

		if (nextNodes.isEmpty()) {
			return;
		}

		for (Node nextNode : nextNodes) {
			if (!_evaluateCondition(
					aiTaskContext, aiTaskResponseBuilder, debug, input,
					nextNode)) {

				continue;
			}

			_executeTaskNode(
				aiTaskContext, aiTaskResponseBuilder, debug, edges, input,
				nextNode, nodes);
		}
	}

	private List<Node> _getNextNodes(Edge[] edges, String id, Node[] nodes) {
		List<Node> nextNodes = new ArrayList<>();

		if (edges == null) {
			return nextNodes;
		}

		for (Edge edge : edges) {
			if (Objects.equals(edge.getSource(), id)) {
				nextNodes.add(_getNode(edge.getTarget(), nodes));
			}
		}

		return nextNodes;
	}

	private Node _getNode(String id, Node[] nodes) {
		for (Node node : nodes) {
			if (Objects.equals(node.getId(), id)) {
				return node;
			}
		}

		return null;
	}

	private Node _getStartNode(Configuration configuration) {
		for (Node node : configuration.getNodes()) {
			if (Objects.equals(node.getId(), configuration.getStartNodeId())) {
				return node;
			}
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AITaskRequestExecutorImpl.class);

	@Reference
	private AITaskResponseBuilderFactory _aiTaskResponseBuilderFactory;

	@Reference
	private JSONFactory _jsonFactory;

	private ServiceTrackerMap<String, AITaskNode> _serviceTrackerMap;

}