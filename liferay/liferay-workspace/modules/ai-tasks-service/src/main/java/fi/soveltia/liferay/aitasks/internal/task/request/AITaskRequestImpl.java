
package fi.soveltia.liferay.aitasks.internal.task.request;

import fi.soveltia.liferay.aitasks.model.AITask;
import fi.soveltia.liferay.aitasks.task.context.AITaskContext;
import fi.soveltia.liferay.aitasks.task.request.AITaskRequest;

import java.util.Map;

/**
 * @author Petteri Karttunen
 */
public class AITaskRequestImpl implements AITaskRequest {

	public AITaskRequestImpl(
		AITask aiTask, AITaskContext aiTaskContext, Map<String, Object> input) {

		_aiTask = aiTask;
		_aiTaskContext = aiTaskContext;
		_input = input;
	}

	public AITask getAITask() {
		return _aiTask;
	}

	@Override
	public AITaskContext getAITaskContext() {
		return _aiTaskContext;
	}

	public Map<String, Object> getInput() {
		return _input;
	}

	private AITaskRequestImpl() {
	}

	private AITask _aiTask;
	private AITaskContext _aiTaskContext;
	private Map<String, Object> _input;

}