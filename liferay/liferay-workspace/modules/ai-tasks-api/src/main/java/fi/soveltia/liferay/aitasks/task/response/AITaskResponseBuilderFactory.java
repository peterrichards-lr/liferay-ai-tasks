package fi.soveltia.liferay.aitasks.task.response;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Petteri Karttunen
 */
@ProviderType
public interface AITaskResponseBuilderFactory {

	public AITaskResponseBuilder builder();

}