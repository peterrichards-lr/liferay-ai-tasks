
package fi.soveltia.liferay.aitasks.rest.internal.dto.v1_0.converter;

import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import fi.soveltia.liferay.aitasks.rest.dto.v1_0.AITask;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.Configuration;
import fi.soveltia.liferay.aitasks.service.AITaskLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "dto.class.name=fi.soveltia.liferay.aitasks.model.AITask",
	service = DTOConverter.class
)
public class AITaskDTOConverter
	implements DTOConverter<fi.soveltia.liferay.aitasks.model.AITask, AITask> {

	@Override
	public String getContentType() {
		return AITask.class.getSimpleName();
	}

	@Override
	public AITask toDTO(fi.soveltia.liferay.aitasks.model.AITask aiTask) {
		return new AITask() {
			{
				setConfiguration(
					_toConfiguration(aiTask.getConfigurationJSON()));
				setCreateDate(aiTask::getCreateDate);
				setDescription(aiTask::getDescription);
				setDescription_i18n(
					() -> LocalizedMapUtil.getI18nMap(
						true, aiTask.getDescriptionMap()));
				setEnabled(aiTask::getEnabled);
				setExternalReferenceCode(aiTask::getExternalReferenceCode);
				setId(aiTask::getAITaskId);
				setModifiedDate(aiTask::getModifiedDate);
				setReadOnly(aiTask::getReadOnly);
				setSchemaVersion(aiTask::getSchemaVersion);
				setTitle(aiTask::getTitle);
				setTitle_i18n(
					() -> LocalizedMapUtil.getI18nMap(
						true, aiTask.getTitleMap()));
				setVersion(aiTask::getVersion);
			}
		};
	}

	@Override
	public AITask toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		fi.soveltia.liferay.aitasks.model.AITask aiTask =
			_aiTaskLocalService.getAITask((Long)dtoConverterContext.getId());

		return toDTO(dtoConverterContext, aiTask);
	}

	@Override
	public AITask toDTO(
			DTOConverterContext dtoConverterContext,
			fi.soveltia.liferay.aitasks.model.AITask aiTask)
		throws Exception {

		return new AITask() {
			{
				setConfiguration(
					_toConfiguration(aiTask.getConfigurationJSON()));
				setCreateDate(aiTask::getCreateDate);
				setDescription(
					() -> _language.get(
						dtoConverterContext.getLocale(),
						aiTask.getDescription(
							dtoConverterContext.getLocale())));
				setDescription_i18n(
					() -> LocalizedMapUtil.getI18nMap(
						dtoConverterContext.isAcceptAllLanguages(),
						aiTask.getDescriptionMap()));
				setEnabled(aiTask::getEnabled);
				setExternalReferenceCode(aiTask::getExternalReferenceCode);
				setId(aiTask::getAITaskId);
				setModifiedDate(aiTask::getModifiedDate);
				setReadOnly(aiTask::getReadOnly);
				setSchemaVersion(aiTask::getSchemaVersion);
				setTitle(
					() -> _language.get(
						dtoConverterContext.getLocale(),
						aiTask.getTitle(dtoConverterContext.getLocale())));
				setTitle_i18n(
					() -> LocalizedMapUtil.getI18nMap(
						dtoConverterContext.isAcceptAllLanguages(),
						aiTask.getTitleMap()));
				setVersion(aiTask::getVersion);
			}
		};
	}

	private Configuration _toConfiguration(String json) {
		if (Validator.isNull(json)) {
			return null;
		}

		Configuration configuration = Configuration.unsafeToDTO(json);

		if (configuration == null) {
			return null;
		}

		return configuration;
	}

	@Reference
	private AITaskLocalService _aiTaskLocalService;

	@Reference
	private Language _language;

}