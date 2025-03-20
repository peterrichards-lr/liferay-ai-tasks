package fi.soveltia.liferay.aitasks.internal.configuration;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;

import fi.soveltia.liferay.aitasks.configuration.AITasksConfiguration;
import fi.soveltia.liferay.aitasks.configuration.AITasksConfigurationProvider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(service = AITasksConfigurationProvider.class)
public class AITasksConfigurationProviderImpl
	implements AITasksConfigurationProvider {

	@Override
	public AITasksConfiguration getCompanyConfiguration(long companyId) {
		return _getAITasksConfiguration(companyId);
	}

	@Override
	public AITasksConfiguration getSystemConfiguration() {
		return _getAITasksConfiguration(CompanyConstants.SYSTEM);
	}

	private AITasksConfiguration _getAITasksConfiguration(long companyId) {
		try {
			if (companyId > CompanyConstants.SYSTEM) {
				return _configurationProvider.getCompanyConfiguration(
					AITasksConfiguration.class, companyId);
			}

			return _configurationProvider.getSystemConfiguration(
				AITasksConfiguration.class);
		}
		catch (ConfigurationException configurationException) {
			return ReflectionUtil.throwException(configurationException);
		}
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

}