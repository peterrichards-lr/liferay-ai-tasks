package fi.soveltia.liferay.aitasks.task.request;

import fi.soveltia.liferay.aitasks.model.AITask;
import fi.soveltia.liferay.aitasks.task.context.AITaskContext;

import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Petteri Karttunen
 */
@ProviderType
public interface AITaskRequest {

	public AITask getAITask();

	public AITaskContext getAITaskContext();

	public Map<String, Object> getInput();

}