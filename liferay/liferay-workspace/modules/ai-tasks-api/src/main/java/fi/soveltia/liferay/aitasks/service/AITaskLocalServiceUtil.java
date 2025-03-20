/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;

import fi.soveltia.liferay.aitasks.model.AITask;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service utility for AITask. This utility wraps
 * <code>fi.soveltia.liferay.aitasks.service.impl.AITaskLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AITaskLocalService
 * @generated
 */
public class AITaskLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>fi.soveltia.liferay.aitasks.service.impl.AITaskLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static AITask addAITask(AITask aiTask) {
		return getService().addAITask(aiTask);
	}

	public static AITask addAITask(
			String configurationJSON,
			Map<java.util.Locale, String> descriptionMap, boolean enabled,
			String externalReferenceCode, boolean readOnly,
			String schemaVersion,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			Map<java.util.Locale, String> titleMap, long userId)
		throws PortalException {

		return getService().addAITask(
			configurationJSON, descriptionMap, enabled, externalReferenceCode,
			readOnly, schemaVersion, serviceContext, titleMap, userId);
	}

	/**
	 * Creates a new ai task with the primary key. Does not add the ai task to the database.
	 *
	 * @param aiTaskId the primary key for the new ai task
	 * @return the new ai task
	 */
	public static AITask createAITask(long aiTaskId) {
		return getService().createAITask(aiTaskId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
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
	public static AITask deleteAITask(AITask aiTask) throws PortalException {
		return getService().deleteAITask(aiTask);
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
	public static AITask deleteAITask(long aiTaskId) throws PortalException {
		return getService().deleteAITask(aiTaskId);
	}

	public static void deleteCompanyAITasks(long companyId)
		throws PortalException {

		getService().deleteCompanyAITasks(companyId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static AITask fetchAITask(long aiTaskId) {
		return getService().fetchAITask(aiTaskId);
	}

	public static AITask fetchAITaskByExternalReferenceCode(
		String externalReferenceCode, long companyId) {

		return getService().fetchAITaskByExternalReferenceCode(
			externalReferenceCode, companyId);
	}

	/**
	 * Returns the ai task with the matching UUID and company.
	 *
	 * @param uuid the ai task's UUID
	 * @param companyId the primary key of the company
	 * @return the matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public static AITask fetchAITaskByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchAITaskByUuidAndCompanyId(uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the ai task with the primary key.
	 *
	 * @param aiTaskId the primary key of the ai task
	 * @return the ai task
	 * @throws PortalException if a ai task with the primary key could not be found
	 */
	public static AITask getAITask(long aiTaskId) throws PortalException {
		return getService().getAITask(aiTaskId);
	}

	public static AITask getAITaskByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException {

		return getService().getAITaskByExternalReferenceCode(
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
	public static AITask getAITaskByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getAITaskByUuidAndCompanyId(uuid, companyId);
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
	public static List<AITask> getAITasks(int start, int end) {
		return getService().getAITasks(start, end);
	}

	public static List<AITask> getAITasks(long companyId, boolean readOnly) {
		return getService().getAITasks(companyId, readOnly);
	}

	/**
	 * Returns the number of ai tasks.
	 *
	 * @return the number of ai tasks
	 */
	public static int getAITasksCount() {
		return getService().getAITasksCount();
	}

	public static int getAITasksCount(long companyId, boolean readOnly) {
		return getService().getAITasksCount(companyId, readOnly);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
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
	public static AITask updateAITask(AITask aiTask) {
		return getService().updateAITask(aiTask);
	}

	public static AITask updateAITask(
			String configurationJSON,
			Map<java.util.Locale, String> descriptionMap, boolean enabled,
			String externalReferenceCode, long aiTaskId, String schemaVersion,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			Map<java.util.Locale, String> titleMap)
		throws PortalException {

		return getService().updateAITask(
			configurationJSON, descriptionMap, enabled, externalReferenceCode,
			aiTaskId, schemaVersion, serviceContext, titleMap);
	}

	public static AITask updateStatus(
			long aiTaskId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			int status, long userId)
		throws PortalException {

		return getService().updateStatus(
			aiTaskId, serviceContext, status, userId);
	}

	public static AITaskLocalService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<AITaskLocalService> _serviceSnapshot =
		new Snapshot<>(AITaskLocalServiceUtil.class, AITaskLocalService.class);

}