package fi.soveltia.liferay.aitasks.task.request;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Petteri Karttunen
 */
@ProviderType
public interface AITaskRequestBuilderFactory {

	public AITaskRequestBuilder builder();

}