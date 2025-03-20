
package fi.soveltia.liferay.aitasks.internal.task.request;

import fi.soveltia.liferay.aitasks.model.AITask;
import fi.soveltia.liferay.aitasks.task.context.AITaskContext;
import fi.soveltia.liferay.aitasks.task.request.AITaskRequest;
import fi.soveltia.liferay.aitasks.task.request.AITaskRequestBuilder;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(service = AITaskRequestBuilder.class)
public class AITaskRequestBuilderImpl implements AITaskRequestBuilder {

	@Override
	public AITaskRequestBuilder aiTask(AITask aiTask) {
		_aiTask = aiTask;

		return this;
	}

	@Override
	public AITaskRequestBuilder aiTaskContext(AITaskContext aiTaskContext) {
		_aiTaskContext = aiTaskContext;

		return this;
	}

	@Override
	public AITaskRequest build() {
		return new AITaskRequestImpl(_aiTask, _aiTaskContext, _input);
	}

	@Override
	public AITaskRequestBuilder input(Map<String, Object> input) {
		_input = input;

		return this;
	}

	private AITask _aiTask;
	private AITaskContext _aiTaskContext;
	private Map<String, Object> _input;

}