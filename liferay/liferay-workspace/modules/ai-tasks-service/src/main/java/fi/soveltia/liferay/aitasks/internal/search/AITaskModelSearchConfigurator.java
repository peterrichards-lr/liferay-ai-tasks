

package fi.soveltia.liferay.aitasks.internal.search;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchConfigurator;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import fi.soveltia.liferay.aitasks.internal.search.spi.model.index.contributor.AITaskModelIndexerWriterContributor;
import fi.soveltia.liferay.aitasks.internal.search.spi.model.result.contributor.AITaskModelSummaryContributor;
import fi.soveltia.liferay.aitasks.model.AITask;
import fi.soveltia.liferay.aitasks.service.AITaskLocalService;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(service = ModelSearchConfigurator.class)
public class AITaskModelSearchConfigurator
	implements ModelSearchConfigurator<AITask> {

	@Override
	public String getClassName() {
		return AITask.class.getName();
	}

	@Override
	public String[] getDefaultSelectedFieldNames() {
		return new String[] {
			Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
			Field.GROUP_ID, Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID,
			Field.STATUS, Field.UID
		};
	}

	@Override
	public String[] getDefaultSelectedLocalizedFieldNames() {
		return new String[] {Field.DESCRIPTION, Field.TITLE};
	}

	@Override
	public ModelIndexerWriterContributor<AITask>
		getModelIndexerWriterContributor() {

		return _modelIndexWriterContributor;
	}

	@Override
	public ModelSummaryContributor getModelSummaryContributor() {
		return _modelSummaryContributor;
	}

	@Activate
	protected void activate() {
		_modelIndexWriterContributor = new AITaskModelIndexerWriterContributor(
			_dynamicQueryBatchIndexingActionableFactory, _aiTaskLocalService);
		_modelSummaryContributor = new AITaskModelSummaryContributor(
			_localization);
	}

	@Reference
	private AITaskLocalService _aiTaskLocalService;

	@Reference
	private DynamicQueryBatchIndexingActionableFactory
		_dynamicQueryBatchIndexingActionableFactory;

	@Reference
	private Localization _localization;

	private ModelIndexerWriterContributor<AITask> _modelIndexWriterContributor;
	private ModelSummaryContributor _modelSummaryContributor;

}