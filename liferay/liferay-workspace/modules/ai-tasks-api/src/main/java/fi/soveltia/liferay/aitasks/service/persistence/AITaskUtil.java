/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import fi.soveltia.liferay.aitasks.model.AITask;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the ai task service. This utility wraps <code>fi.soveltia.liferay.aitasks.service.persistence.impl.AITaskPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AITaskPersistence
 * @generated
 */
public class AITaskUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AITask aiTask) {
		getPersistence().clearCache(aiTask);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, AITask> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AITask> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AITask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AITask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AITask> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AITask update(AITask aiTask) {
		return getPersistence().update(aiTask);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AITask update(AITask aiTask, ServiceContext serviceContext) {
		return getPersistence().update(aiTask, serviceContext);
	}

	/**
	 * Returns all the ai tasks where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching ai tasks
	 */
	public static List<AITask> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the ai tasks where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @return the range of matching ai tasks
	 */
	public static List<AITask> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the ai tasks where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ai tasks
	 */
	public static List<AITask> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AITask> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ai tasks where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ai tasks
	 */
	public static List<AITask> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AITask> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first ai task in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public static AITask findByUuid_First(
			String uuid, OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first ai task in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public static AITask fetchByUuid_First(
		String uuid, OrderByComparator<AITask> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last ai task in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public static AITask findByUuid_Last(
			String uuid, OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last ai task in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public static AITask fetchByUuid_Last(
		String uuid, OrderByComparator<AITask> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the ai tasks before and after the current ai task in the ordered set where uuid = &#63;.
	 *
	 * @param aiTaskId the primary key of the current ai task
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public static AITask[] findByUuid_PrevAndNext(
			long aiTaskId, String uuid,
			OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByUuid_PrevAndNext(
			aiTaskId, uuid, orderByComparator);
	}

	/**
	 * Returns all the ai tasks that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching ai tasks that the user has permission to view
	 */
	public static List<AITask> filterFindByUuid(String uuid) {
		return getPersistence().filterFindByUuid(uuid);
	}

	/**
	 * Returns a range of all the ai tasks that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @return the range of matching ai tasks that the user has permission to view
	 */
	public static List<AITask> filterFindByUuid(
		String uuid, int start, int end) {

		return getPersistence().filterFindByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the ai tasks that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ai tasks that the user has permission to view
	 */
	public static List<AITask> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<AITask> orderByComparator) {

		return getPersistence().filterFindByUuid(
			uuid, start, end, orderByComparator);
	}

	/**
	 * Returns the ai tasks before and after the current ai task in the ordered set of ai tasks that the user has permission to view where uuid = &#63;.
	 *
	 * @param aiTaskId the primary key of the current ai task
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public static AITask[] filterFindByUuid_PrevAndNext(
			long aiTaskId, String uuid,
			OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().filterFindByUuid_PrevAndNext(
			aiTaskId, uuid, orderByComparator);
	}

	/**
	 * Removes all the ai tasks where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of ai tasks where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching ai tasks
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the number of ai tasks that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching ai tasks that the user has permission to view
	 */
	public static int filterCountByUuid(String uuid) {
		return getPersistence().filterCountByUuid(uuid);
	}

	/**
	 * Returns all the ai tasks where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching ai tasks
	 */
	public static List<AITask> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the ai tasks where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @return the range of matching ai tasks
	 */
	public static List<AITask> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the ai tasks where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ai tasks
	 */
	public static List<AITask> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AITask> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ai tasks where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ai tasks
	 */
	public static List<AITask> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AITask> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first ai task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public static AITask findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first ai task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public static AITask fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<AITask> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last ai task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public static AITask findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last ai task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public static AITask fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<AITask> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the ai tasks before and after the current ai task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param aiTaskId the primary key of the current ai task
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public static AITask[] findByUuid_C_PrevAndNext(
			long aiTaskId, String uuid, long companyId,
			OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByUuid_C_PrevAndNext(
			aiTaskId, uuid, companyId, orderByComparator);
	}

	/**
	 * Returns all the ai tasks that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching ai tasks that the user has permission to view
	 */
	public static List<AITask> filterFindByUuid_C(String uuid, long companyId) {
		return getPersistence().filterFindByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the ai tasks that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @return the range of matching ai tasks that the user has permission to view
	 */
	public static List<AITask> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().filterFindByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the ai tasks that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ai tasks that the user has permission to view
	 */
	public static List<AITask> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AITask> orderByComparator) {

		return getPersistence().filterFindByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the ai tasks before and after the current ai task in the ordered set of ai tasks that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param aiTaskId the primary key of the current ai task
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public static AITask[] filterFindByUuid_C_PrevAndNext(
			long aiTaskId, String uuid, long companyId,
			OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().filterFindByUuid_C_PrevAndNext(
			aiTaskId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the ai tasks where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of ai tasks where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching ai tasks
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of ai tasks that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching ai tasks that the user has permission to view
	 */
	public static int filterCountByUuid_C(String uuid, long companyId) {
		return getPersistence().filterCountByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the ai tasks where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching ai tasks
	 */
	public static List<AITask> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the ai tasks where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @return the range of matching ai tasks
	 */
	public static List<AITask> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the ai tasks where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ai tasks
	 */
	public static List<AITask> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<AITask> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ai tasks where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ai tasks
	 */
	public static List<AITask> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<AITask> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first ai task in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public static AITask findByCompanyId_First(
			long companyId, OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first ai task in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public static AITask fetchByCompanyId_First(
		long companyId, OrderByComparator<AITask> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last ai task in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public static AITask findByCompanyId_Last(
			long companyId, OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last ai task in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public static AITask fetchByCompanyId_Last(
		long companyId, OrderByComparator<AITask> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the ai tasks before and after the current ai task in the ordered set where companyId = &#63;.
	 *
	 * @param aiTaskId the primary key of the current ai task
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public static AITask[] findByCompanyId_PrevAndNext(
			long aiTaskId, long companyId,
			OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByCompanyId_PrevAndNext(
			aiTaskId, companyId, orderByComparator);
	}

	/**
	 * Returns all the ai tasks that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching ai tasks that the user has permission to view
	 */
	public static List<AITask> filterFindByCompanyId(long companyId) {
		return getPersistence().filterFindByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the ai tasks that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @return the range of matching ai tasks that the user has permission to view
	 */
	public static List<AITask> filterFindByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().filterFindByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the ai tasks that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ai tasks that the user has permission to view
	 */
	public static List<AITask> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<AITask> orderByComparator) {

		return getPersistence().filterFindByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the ai tasks before and after the current ai task in the ordered set of ai tasks that the user has permission to view where companyId = &#63;.
	 *
	 * @param aiTaskId the primary key of the current ai task
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public static AITask[] filterFindByCompanyId_PrevAndNext(
			long aiTaskId, long companyId,
			OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().filterFindByCompanyId_PrevAndNext(
			aiTaskId, companyId, orderByComparator);
	}

	/**
	 * Removes all the ai tasks where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of ai tasks where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching ai tasks
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Returns the number of ai tasks that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching ai tasks that the user has permission to view
	 */
	public static int filterCountByCompanyId(long companyId) {
		return getPersistence().filterCountByCompanyId(companyId);
	}

	/**
	 * Returns all the ai tasks where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @return the matching ai tasks
	 */
	public static List<AITask> findByC_R(long companyId, boolean readOnly) {
		return getPersistence().findByC_R(companyId, readOnly);
	}

	/**
	 * Returns a range of all the ai tasks where companyId = &#63; and readOnly = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @return the range of matching ai tasks
	 */
	public static List<AITask> findByC_R(
		long companyId, boolean readOnly, int start, int end) {

		return getPersistence().findByC_R(companyId, readOnly, start, end);
	}

	/**
	 * Returns an ordered range of all the ai tasks where companyId = &#63; and readOnly = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ai tasks
	 */
	public static List<AITask> findByC_R(
		long companyId, boolean readOnly, int start, int end,
		OrderByComparator<AITask> orderByComparator) {

		return getPersistence().findByC_R(
			companyId, readOnly, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ai tasks where companyId = &#63; and readOnly = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ai tasks
	 */
	public static List<AITask> findByC_R(
		long companyId, boolean readOnly, int start, int end,
		OrderByComparator<AITask> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByC_R(
			companyId, readOnly, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first ai task in the ordered set where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public static AITask findByC_R_First(
			long companyId, boolean readOnly,
			OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByC_R_First(
			companyId, readOnly, orderByComparator);
	}

	/**
	 * Returns the first ai task in the ordered set where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public static AITask fetchByC_R_First(
		long companyId, boolean readOnly,
		OrderByComparator<AITask> orderByComparator) {

		return getPersistence().fetchByC_R_First(
			companyId, readOnly, orderByComparator);
	}

	/**
	 * Returns the last ai task in the ordered set where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public static AITask findByC_R_Last(
			long companyId, boolean readOnly,
			OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByC_R_Last(
			companyId, readOnly, orderByComparator);
	}

	/**
	 * Returns the last ai task in the ordered set where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public static AITask fetchByC_R_Last(
		long companyId, boolean readOnly,
		OrderByComparator<AITask> orderByComparator) {

		return getPersistence().fetchByC_R_Last(
			companyId, readOnly, orderByComparator);
	}

	/**
	 * Returns the ai tasks before and after the current ai task in the ordered set where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param aiTaskId the primary key of the current ai task
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public static AITask[] findByC_R_PrevAndNext(
			long aiTaskId, long companyId, boolean readOnly,
			OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByC_R_PrevAndNext(
			aiTaskId, companyId, readOnly, orderByComparator);
	}

	/**
	 * Returns all the ai tasks that the user has permission to view where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @return the matching ai tasks that the user has permission to view
	 */
	public static List<AITask> filterFindByC_R(
		long companyId, boolean readOnly) {

		return getPersistence().filterFindByC_R(companyId, readOnly);
	}

	/**
	 * Returns a range of all the ai tasks that the user has permission to view where companyId = &#63; and readOnly = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @return the range of matching ai tasks that the user has permission to view
	 */
	public static List<AITask> filterFindByC_R(
		long companyId, boolean readOnly, int start, int end) {

		return getPersistence().filterFindByC_R(
			companyId, readOnly, start, end);
	}

	/**
	 * Returns an ordered range of all the ai tasks that the user has permissions to view where companyId = &#63; and readOnly = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ai tasks that the user has permission to view
	 */
	public static List<AITask> filterFindByC_R(
		long companyId, boolean readOnly, int start, int end,
		OrderByComparator<AITask> orderByComparator) {

		return getPersistence().filterFindByC_R(
			companyId, readOnly, start, end, orderByComparator);
	}

	/**
	 * Returns the ai tasks before and after the current ai task in the ordered set of ai tasks that the user has permission to view where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param aiTaskId the primary key of the current ai task
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public static AITask[] filterFindByC_R_PrevAndNext(
			long aiTaskId, long companyId, boolean readOnly,
			OrderByComparator<AITask> orderByComparator)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().filterFindByC_R_PrevAndNext(
			aiTaskId, companyId, readOnly, orderByComparator);
	}

	/**
	 * Removes all the ai tasks where companyId = &#63; and readOnly = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 */
	public static void removeByC_R(long companyId, boolean readOnly) {
		getPersistence().removeByC_R(companyId, readOnly);
	}

	/**
	 * Returns the number of ai tasks where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @return the number of matching ai tasks
	 */
	public static int countByC_R(long companyId, boolean readOnly) {
		return getPersistence().countByC_R(companyId, readOnly);
	}

	/**
	 * Returns the number of ai tasks that the user has permission to view where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @return the number of matching ai tasks that the user has permission to view
	 */
	public static int filterCountByC_R(long companyId, boolean readOnly) {
		return getPersistence().filterCountByC_R(companyId, readOnly);
	}

	/**
	 * Returns the ai task where externalReferenceCode = &#63; and companyId = &#63; or throws a <code>NoSuchAITaskException</code> if it could not be found.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public static AITask findByERC_C(
			String externalReferenceCode, long companyId)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByERC_C(externalReferenceCode, companyId);
	}

	/**
	 * Returns the ai task where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public static AITask fetchByERC_C(
		String externalReferenceCode, long companyId) {

		return getPersistence().fetchByERC_C(externalReferenceCode, companyId);
	}

	/**
	 * Returns the ai task where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public static AITask fetchByERC_C(
		String externalReferenceCode, long companyId, boolean useFinderCache) {

		return getPersistence().fetchByERC_C(
			externalReferenceCode, companyId, useFinderCache);
	}

	/**
	 * Removes the ai task where externalReferenceCode = &#63; and companyId = &#63; from the database.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the ai task that was removed
	 */
	public static AITask removeByERC_C(
			String externalReferenceCode, long companyId)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().removeByERC_C(externalReferenceCode, companyId);
	}

	/**
	 * Returns the number of ai tasks where externalReferenceCode = &#63; and companyId = &#63;.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the number of matching ai tasks
	 */
	public static int countByERC_C(
		String externalReferenceCode, long companyId) {

		return getPersistence().countByERC_C(externalReferenceCode, companyId);
	}

	/**
	 * Caches the ai task in the entity cache if it is enabled.
	 *
	 * @param aiTask the ai task
	 */
	public static void cacheResult(AITask aiTask) {
		getPersistence().cacheResult(aiTask);
	}

	/**
	 * Caches the ai tasks in the entity cache if it is enabled.
	 *
	 * @param aiTasks the ai tasks
	 */
	public static void cacheResult(List<AITask> aiTasks) {
		getPersistence().cacheResult(aiTasks);
	}

	/**
	 * Creates a new ai task with the primary key. Does not add the ai task to the database.
	 *
	 * @param aiTaskId the primary key for the new ai task
	 * @return the new ai task
	 */
	public static AITask create(long aiTaskId) {
		return getPersistence().create(aiTaskId);
	}

	/**
	 * Removes the ai task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param aiTaskId the primary key of the ai task
	 * @return the ai task that was removed
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public static AITask remove(long aiTaskId)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().remove(aiTaskId);
	}

	public static AITask updateImpl(AITask aiTask) {
		return getPersistence().updateImpl(aiTask);
	}

	/**
	 * Returns the ai task with the primary key or throws a <code>NoSuchAITaskException</code> if it could not be found.
	 *
	 * @param aiTaskId the primary key of the ai task
	 * @return the ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public static AITask findByPrimaryKey(long aiTaskId)
		throws fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException {

		return getPersistence().findByPrimaryKey(aiTaskId);
	}

	/**
	 * Returns the ai task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param aiTaskId the primary key of the ai task
	 * @return the ai task, or <code>null</code> if a ai task with the primary key could not be found
	 */
	public static AITask fetchByPrimaryKey(long aiTaskId) {
		return getPersistence().fetchByPrimaryKey(aiTaskId);
	}

	/**
	 * Returns all the ai tasks.
	 *
	 * @return the ai tasks
	 */
	public static List<AITask> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the ai tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @return the range of ai tasks
	 */
	public static List<AITask> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the ai tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ai tasks
	 */
	public static List<AITask> findAll(
		int start, int end, OrderByComparator<AITask> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ai tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AITaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ai tasks
	 * @param end the upper bound of the range of ai tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ai tasks
	 */
	public static List<AITask> findAll(
		int start, int end, OrderByComparator<AITask> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the ai tasks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of ai tasks.
	 *
	 * @return the number of ai tasks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AITaskPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(AITaskPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile AITaskPersistence _persistence;

}