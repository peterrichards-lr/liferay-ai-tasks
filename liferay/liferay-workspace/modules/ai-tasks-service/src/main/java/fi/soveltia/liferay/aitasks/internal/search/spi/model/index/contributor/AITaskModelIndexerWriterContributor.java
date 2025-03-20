
package fi.soveltia.liferay.aitasks.internal.search.spi.model.index.contributor;

import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

import fi.soveltia.liferay.aitasks.model.AITask;
import fi.soveltia.liferay.aitasks.service.AITaskLocalService;

/**
 * @author Petteri Karttunen
 */
public class AITaskModelIndexerWriterContributor
	implements ModelIndexerWriterContributor<AITask> {

	public AITaskModelIndexerWriterContributor(
		DynamicQueryBatchIndexingActionableFactory
			dynamicQueryBatchIndexingActionableFactory,
		AITaskLocalService aiTaskLocalService) {

		_dynamicQueryBatchIndexingActionableFactory =
			dynamicQueryBatchIndexingActionableFactory;
		_aiTaskLocalService = aiTaskLocalService;
	}

	@Override
	public void customize(
		BatchIndexingActionable batchIndexingActionable,
		ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

		batchIndexingActionable.setPerformActionMethod(
			(AITask aiTask) -> batchIndexingActionable.addDocuments(
				modelIndexerWriterDocumentHelper.getDocument(aiTask)));
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {
		return _dynamicQueryBatchIndexingActionableFactory.
			getBatchIndexingActionable(
				_aiTaskLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(AITask aiTask) {
		return aiTask.getCompanyId();
	}

	private final AITaskLocalService _aiTaskLocalService;
	private final DynamicQueryBatchIndexingActionableFactory
		_dynamicQueryBatchIndexingActionableFactory;

}