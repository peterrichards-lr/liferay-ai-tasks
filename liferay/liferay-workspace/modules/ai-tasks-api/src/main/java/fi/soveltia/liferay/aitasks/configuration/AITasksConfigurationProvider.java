package fi.soveltia.liferay.aitasks.configuration;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Petteri Karttunen
 */
@ProviderType
public interface AITasksConfigurationProvider {

	public AITasksConfiguration getCompanyConfiguration(long companyId);

	public AITasksConfiguration getSystemConfiguration();

}