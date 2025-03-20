/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException;
import fi.soveltia.liferay.aitasks.model.AITask;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the ai task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AITaskUtil
 * @generated
 */
@ProviderType
public interface AITaskPersistence extends BasePersistence<AITask> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AITaskUtil} to access the ai task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the ai tasks where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching ai tasks
	 */
	public java.util.List<AITask> findByUuid(String uuid);

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
	public java.util.List<AITask> findByUuid(String uuid, int start, int end);

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
	public java.util.List<AITask> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

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
	public java.util.List<AITask> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ai task in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public AITask findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Returns the first ai task in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public AITask fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

	/**
	 * Returns the last ai task in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public AITask findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Returns the last ai task in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public AITask fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

	/**
	 * Returns the ai tasks before and after the current ai task in the ordered set where uuid = &#63;.
	 *
	 * @param aiTaskId the primary key of the current ai task
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public AITask[] findByUuid_PrevAndNext(
			long aiTaskId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Returns all the ai tasks that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching ai tasks that the user has permission to view
	 */
	public java.util.List<AITask> filterFindByUuid(String uuid);

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
	public java.util.List<AITask> filterFindByUuid(
		String uuid, int start, int end);

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
	public java.util.List<AITask> filterFindByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

	/**
	 * Returns the ai tasks before and after the current ai task in the ordered set of ai tasks that the user has permission to view where uuid = &#63;.
	 *
	 * @param aiTaskId the primary key of the current ai task
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public AITask[] filterFindByUuid_PrevAndNext(
			long aiTaskId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Removes all the ai tasks where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of ai tasks where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching ai tasks
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the number of ai tasks that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching ai tasks that the user has permission to view
	 */
	public int filterCountByUuid(String uuid);

	/**
	 * Returns all the ai tasks where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching ai tasks
	 */
	public java.util.List<AITask> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<AITask> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<AITask> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

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
	public java.util.List<AITask> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ai task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public AITask findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Returns the first ai task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public AITask fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

	/**
	 * Returns the last ai task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public AITask findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Returns the last ai task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public AITask fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

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
	public AITask[] findByUuid_C_PrevAndNext(
			long aiTaskId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Returns all the ai tasks that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching ai tasks that the user has permission to view
	 */
	public java.util.List<AITask> filterFindByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<AITask> filterFindByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<AITask> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

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
	public AITask[] filterFindByUuid_C_PrevAndNext(
			long aiTaskId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Removes all the ai tasks where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of ai tasks where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching ai tasks
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of ai tasks that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching ai tasks that the user has permission to view
	 */
	public int filterCountByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the ai tasks where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching ai tasks
	 */
	public java.util.List<AITask> findByCompanyId(long companyId);

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
	public java.util.List<AITask> findByCompanyId(
		long companyId, int start, int end);

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
	public java.util.List<AITask> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

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
	public java.util.List<AITask> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ai task in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public AITask findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Returns the first ai task in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public AITask fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

	/**
	 * Returns the last ai task in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public AITask findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Returns the last ai task in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public AITask fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

	/**
	 * Returns the ai tasks before and after the current ai task in the ordered set where companyId = &#63;.
	 *
	 * @param aiTaskId the primary key of the current ai task
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public AITask[] findByCompanyId_PrevAndNext(
			long aiTaskId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Returns all the ai tasks that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching ai tasks that the user has permission to view
	 */
	public java.util.List<AITask> filterFindByCompanyId(long companyId);

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
	public java.util.List<AITask> filterFindByCompanyId(
		long companyId, int start, int end);

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
	public java.util.List<AITask> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

	/**
	 * Returns the ai tasks before and after the current ai task in the ordered set of ai tasks that the user has permission to view where companyId = &#63;.
	 *
	 * @param aiTaskId the primary key of the current ai task
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public AITask[] filterFindByCompanyId_PrevAndNext(
			long aiTaskId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Removes all the ai tasks where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of ai tasks where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching ai tasks
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns the number of ai tasks that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching ai tasks that the user has permission to view
	 */
	public int filterCountByCompanyId(long companyId);

	/**
	 * Returns all the ai tasks where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @return the matching ai tasks
	 */
	public java.util.List<AITask> findByC_R(long companyId, boolean readOnly);

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
	public java.util.List<AITask> findByC_R(
		long companyId, boolean readOnly, int start, int end);

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
	public java.util.List<AITask> findByC_R(
		long companyId, boolean readOnly, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

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
	public java.util.List<AITask> findByC_R(
		long companyId, boolean readOnly, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ai task in the ordered set where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public AITask findByC_R_First(
			long companyId, boolean readOnly,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Returns the first ai task in the ordered set where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public AITask fetchByC_R_First(
		long companyId, boolean readOnly,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

	/**
	 * Returns the last ai task in the ordered set where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public AITask findByC_R_Last(
			long companyId, boolean readOnly,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Returns the last ai task in the ordered set where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public AITask fetchByC_R_Last(
		long companyId, boolean readOnly,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

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
	public AITask[] findByC_R_PrevAndNext(
			long aiTaskId, long companyId, boolean readOnly,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Returns all the ai tasks that the user has permission to view where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @return the matching ai tasks that the user has permission to view
	 */
	public java.util.List<AITask> filterFindByC_R(
		long companyId, boolean readOnly);

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
	public java.util.List<AITask> filterFindByC_R(
		long companyId, boolean readOnly, int start, int end);

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
	public java.util.List<AITask> filterFindByC_R(
		long companyId, boolean readOnly, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

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
	public AITask[] filterFindByC_R_PrevAndNext(
			long aiTaskId, long companyId, boolean readOnly,
			com.liferay.portal.kernel.util.OrderByComparator<AITask>
				orderByComparator)
		throws NoSuchAITaskException;

	/**
	 * Removes all the ai tasks where companyId = &#63; and readOnly = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 */
	public void removeByC_R(long companyId, boolean readOnly);

	/**
	 * Returns the number of ai tasks where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @return the number of matching ai tasks
	 */
	public int countByC_R(long companyId, boolean readOnly);

	/**
	 * Returns the number of ai tasks that the user has permission to view where companyId = &#63; and readOnly = &#63;.
	 *
	 * @param companyId the company ID
	 * @param readOnly the read only
	 * @return the number of matching ai tasks that the user has permission to view
	 */
	public int filterCountByC_R(long companyId, boolean readOnly);

	/**
	 * Returns the ai task where externalReferenceCode = &#63; and companyId = &#63; or throws a <code>NoSuchAITaskException</code> if it could not be found.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching ai task
	 * @throws NoSuchAITaskException if a matching ai task could not be found
	 */
	public AITask findByERC_C(String externalReferenceCode, long companyId)
		throws NoSuchAITaskException;

	/**
	 * Returns the ai task where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public AITask fetchByERC_C(String externalReferenceCode, long companyId);

	/**
	 * Returns the ai task where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ai task, or <code>null</code> if a matching ai task could not be found
	 */
	public AITask fetchByERC_C(
		String externalReferenceCode, long companyId, boolean useFinderCache);

	/**
	 * Removes the ai task where externalReferenceCode = &#63; and companyId = &#63; from the database.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the ai task that was removed
	 */
	public AITask removeByERC_C(String externalReferenceCode, long companyId)
		throws NoSuchAITaskException;

	/**
	 * Returns the number of ai tasks where externalReferenceCode = &#63; and companyId = &#63;.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the number of matching ai tasks
	 */
	public int countByERC_C(String externalReferenceCode, long companyId);

	/**
	 * Caches the ai task in the entity cache if it is enabled.
	 *
	 * @param aiTask the ai task
	 */
	public void cacheResult(AITask aiTask);

	/**
	 * Caches the ai tasks in the entity cache if it is enabled.
	 *
	 * @param aiTasks the ai tasks
	 */
	public void cacheResult(java.util.List<AITask> aiTasks);

	/**
	 * Creates a new ai task with the primary key. Does not add the ai task to the database.
	 *
	 * @param aiTaskId the primary key for the new ai task
	 * @return the new ai task
	 */
	public AITask create(long aiTaskId);

	/**
	 * Removes the ai task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param aiTaskId the primary key of the ai task
	 * @return the ai task that was removed
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public AITask remove(long aiTaskId) throws NoSuchAITaskException;

	public AITask updateImpl(AITask aiTask);

	/**
	 * Returns the ai task with the primary key or throws a <code>NoSuchAITaskException</code> if it could not be found.
	 *
	 * @param aiTaskId the primary key of the ai task
	 * @return the ai task
	 * @throws NoSuchAITaskException if a ai task with the primary key could not be found
	 */
	public AITask findByPrimaryKey(long aiTaskId) throws NoSuchAITaskException;

	/**
	 * Returns the ai task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param aiTaskId the primary key of the ai task
	 * @return the ai task, or <code>null</code> if a ai task with the primary key could not be found
	 */
	public AITask fetchByPrimaryKey(long aiTaskId);

	/**
	 * Returns all the ai tasks.
	 *
	 * @return the ai tasks
	 */
	public java.util.List<AITask> findAll();

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
	public java.util.List<AITask> findAll(int start, int end);

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
	public java.util.List<AITask> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator);

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
	public java.util.List<AITask> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AITask>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the ai tasks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of ai tasks.
	 *
	 * @return the number of ai tasks
	 */
	public int countAll();

}