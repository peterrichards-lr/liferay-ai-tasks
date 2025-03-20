
package fi.soveltia.liferay.aitasks.internal.search.spi.model.index.contributor;

import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import fi.soveltia.liferay.aitasks.model.AITask;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "indexer.class.name=fi.soveltia.liferay.aitasks.model.AITask",
	service = ModelDocumentContributor.class
)
public class AITaskModelDocumentContributor
	implements ModelDocumentContributor<AITask> {

	@Override
	public void contribute(Document document, AITask aiTask) {
		document.addDate(Field.MODIFIED_DATE, aiTask.getModifiedDate());
		document.addKeyword(Field.STATUS, aiTask.getStatus());

		for (Locale locale :
				_language.getCompanyAvailableLocales(aiTask.getCompanyId())) {

			String languageId = LocaleUtil.toLanguageId(locale);

			document.addKeyword(
				Field.getSortableFieldName(
					_localization.getLocalizedName(
						Field.DESCRIPTION, languageId)),
				aiTask.getDescription(locale), true);
			document.addKeyword(
				Field.getSortableFieldName(
					_localization.getLocalizedName(Field.TITLE, languageId)),
				aiTask.getTitle(locale), true);
			document.addText(
				_localization.getLocalizedName(Field.DESCRIPTION, languageId),
				aiTask.getDescription(locale));
			document.addText(
				_localization.getLocalizedName(Field.TITLE, languageId),
				aiTask.getTitle(locale));
		}
	}

	@Reference
	private Language _language;

	@Reference
	private Localization _localization;

}