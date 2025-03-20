/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link AITaskLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AITaskLocalService
 * @generated
 */
public class AITaskLocalServiceWrapper
	implements AITaskLocalService, ServiceWrapper<AITaskLocalService> {

	public AITaskLocalServiceWrapper() {
		this(null);
	}

	public AITaskLocalServiceWrapper(AITaskLocalService aiTaskLocalService) {
		_aiTaskLocalService = aiTaskLocalService;
	}

	/**
	 * Adds the ai task to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AITaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param aiTask the ai task
	 * @return the ai task that was added
	 */
	@Override
	public fi.soveltia.liferay.aitasks.model.AITask addAITask(
		fi.soveltia.liferay.aitasks.model.AITask aiTask) {

		return _aiTaskLocalService.addAITask(aiTask);
	}

	@Override
	public fi.soveltia.liferay.aitasks.model.AITask addAITask(
			String configurationJSON,
			java.util.Map<java.util.Locale, String> descriptionMap,
			boolean enabled, String externalReferenceCode, boolean readOnly,
			String schemaVersion,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			java.util.Map<java.util.Locale, String> titleMap, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskLocalService.addAITask(
			configurationJSON, descriptionMap, enabled, externalReferenceCode,
			readOnly, schemaVersion, serviceContext, titleMap, userId);
	}

	/**
	 * Creates a new ai task with the primary key. Does not add the ai task to the database.
	 *
	 * @param aiTaskId the primary key for the new ai task
	 * @return the new ai task
	 */
	@Override
	public fi.soveltia.liferay.aitasks.model.AITask createAITask(
		long aiTaskId) {

		return _aiTaskLocalService.createAITask(aiTaskId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the ai task from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AITaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param aiTask the ai task
	 * @return the ai task that was removed
	 * @throws PortalException
	 */
	@Override
	public fi.soveltia.liferay.aitasks.model.AITask deleteAITask(
			fi.soveltia.liferay.aitasks.model.AITask aiTask)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskLocalService.deleteAITask(aiTask);
	}

	/**
	 * Deletes the ai task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AITaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param aiTaskId the primary key of the ai task
	 * @return the ai task that was removed
	 * @throws PortalException if a ai task with the primary key could not be found
	 */
	@Override
	public fi.soveltia.liferay.aitasks.model.AITask deleteAITask(long aiTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskLocalService.deleteAITask(aiTaskId);
	}

	@Override
	public void deleteCompanyAITasks(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_aiTaskLocalService.deleteCompanyAITasks(companyId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _aiTaskLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _aiTaskLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _aiTaskLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _aiTaskLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fi.soveltia.liferay.aitasks.model.impl.AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _aiTaskLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fi.soveltia.liferay.aitasks.model.impl.AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _aiTaskLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _aiTaskLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _aiTaskLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public fi.soveltia.liferay.aitasks.model.AITask fetchAITask(long aiTaskId) {
		return _aiTaskLocalService.fetchAITask(aiTaskId);
	}

	@Override
	public fi.soveltia.liferay.aitasks.model.AITask
		fetchAITaskByExternalReferenceCode(
			String externalReferenceCode, long companyId) {

		return _aiTaskLocalService.fetchAITaskByExternalReferenceCode(
			externalReferenceCode, companyId);
	}

	/**
	 * Returns the ai task with the matching UUID and company.
	 *
	 * @param uuid the ai task's UUID
	 * @param companyId the primary key of the company
	 * @return the matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	@Override
	public fi.soveltia.liferay.aitasks.model.AITask
		fetchAITaskByUuidAndCompanyId(String uuid, long companyId) {

		return _aiTaskLocalService.fetchAITaskByUuidAndCompanyId(
			uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _aiTaskLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the ai task with the primary key.
	 *
	 * @param aiTaskId the primary key of the ai task
	 * @return the ai task
	 * @throws PortalException if a ai task with the primary key could not be found
	 */
	@Override
	public fi.soveltia.liferay.aitasks.model.AITask getAITask(long aiTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskLocalService.getAITask(aiTaskId);
	}

	@Override
	public fi.soveltia.liferay.aitasks.model.AITask
			getAITaskByExternalReferenceCode(
				String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskLocalService.getAITaskByExternalReferenceCode(
			externalReferenceCode, companyId);
	}

	/**
	 * Returns the ai task with the matching UUID and company.
	 *
	 * @param uuid the ai task's UUID
	 * @param companyId the primary key of the company
	 * @return the matching ai task
	 * @throws PortalException if a matching ai task could not be found
	 */
	@Override
	public fi.soveltia.liferay.aitasks.model.AITask getAITaskByUuidAndCompanyId(
			String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskLocalService.getAITaskByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of all the ai tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fi.soveltia.liferay.aitasks.model.impl.AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @return the range of ai tasks
	 */
	@Override
	public java.util.List<fi.soveltia.liferay.aitasks.model.AITask> getAITasks(
		int start, int end) {

		return _aiTaskLocalService.getAITasks(start, end);
	}

	@Override
	public java.util.List<fi.soveltia.liferay.aitasks.model.AITask> getAITasks(
		long companyId, boolean readOnly) {

		return _aiTaskLocalService.getAITasks(companyId, readOnly);
	}

	/**
	 * Returns the number of ai tasks.
	 *
	 * @return the number of ai tasks
	 */
	@Override
	public int getAITasksCount() {
		return _aiTaskLocalService.getAITasksCount();
	}

	@Override
	public int getAITasksCount(long companyId, boolean readOnly) {
		return _aiTaskLocalService.getAITasksCount(companyId, readOnly);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _aiTaskLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _aiTaskLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _aiTaskLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the ai task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AITaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param aiTask the ai task
	 * @return the ai task that was updated
	 */
	@Override
	public fi.soveltia.liferay.aitasks.model.AITask updateAITask(
		fi.soveltia.liferay.aitasks.model.AITask aiTask) {

		return _aiTaskLocalService.updateAITask(aiTask);
	}

	@Override
	public fi.soveltia.liferay.aitasks.model.AITask updateAITask(
			String configurationJSON,
			java.util.Map<java.util.Locale, String> descriptionMap,
			boolean enabled, String externalReferenceCode, long aiTaskId,
			String schemaVersion,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			java.util.Map<java.util.Locale, String> titleMap)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskLocalService.updateAITask(
			configurationJSON, descriptionMap, enabled, externalReferenceCode,
			aiTaskId, schemaVersion, serviceContext, titleMap);
	}

	@Override
	public fi.soveltia.liferay.aitasks.model.AITask updateStatus(
			long aiTaskId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			int status, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskLocalService.updateStatus(
			aiTaskId, serviceContext, status, userId);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _aiTaskLocalService.getBasePersistence();
	}

	@Override
	public AITaskLocalService getWrappedService() {
		return _aiTaskLocalService;
	}

	@Override
	public void setWrappedService(AITaskLocalService aiTaskLocalService) {
		_aiTaskLocalService = aiTaskLocalService;
	}

	private AITaskLocalService _aiTaskLocalService;

}