package fi.soveltia.liferay.aitasks.task.request;

import fi.soveltia.liferay.aitasks.task.response.AITaskResponse;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Petteri Karttunen
 */
@ProviderType
public interface AITaskRequestExecutor {

	public AITaskResponse execute(AITaskRequest aiTaskRequest);

}