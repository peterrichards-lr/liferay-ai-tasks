/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import fi.soveltia.liferay.aitasks.exception.AITaskTitleException;
import fi.soveltia.liferay.aitasks.model.AITask;
import fi.soveltia.liferay.aitasks.service.base.AITaskLocalServiceBaseImpl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=fi.soveltia.liferay.aitasks.model.AITask",
	service = AopService.class
)
public class AITaskLocalServiceImpl extends AITaskLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AITask addAITask(
			String configurationJSON, Map<Locale, String> descriptionMap,
			boolean enabled, String externalReferenceCode, boolean readOnly,
			String schemaVersion, ServiceContext serviceContext,
			Map<Locale, String> titleMap, long userId)
		throws PortalException {

		_validate(titleMap, serviceContext);

		AITask aiTask = aiTaskPersistence.create(
			counterLocalService.increment());

		User user = _userLocalService.getUser(userId);

		aiTask.setEnabled(enabled);
		aiTask.setExternalReferenceCode(externalReferenceCode);
		aiTask.setCompanyId(user.getCompanyId());
		aiTask.setUserId(user.getUserId());
		aiTask.setUserName(user.getFullName());
		aiTask.setConfigurationJSON(configurationJSON);
		aiTask.setDescriptionMap(descriptionMap);
		aiTask.setReadOnly(readOnly);
		aiTask.setSchemaVersion(schemaVersion);
		aiTask.setTitleMap(titleMap);
		aiTask.setVersion(
			String.format(
				"%.1f", GetterUtil.getFloat(aiTask.getVersion(), 0.9F) + 0.1));
		aiTask.setStatus(WorkflowConstants.STATUS_APPROVED);
		aiTask.setStatusByUserId(user.getUserId());
		aiTask.setStatusDate(serviceContext.getModifiedDate(null));

		aiTask = aiTaskPersistence.update(aiTask);

		_resourceLocalService.addModelResources(aiTask, serviceContext);

		return aiTask;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public AITask deleteAITask(AITask aiTask) throws PortalException {
		aiTask = aiTaskPersistence.remove(aiTask);

		_resourceLocalService.deleteResource(
			aiTask, ResourceConstants.SCOPE_INDIVIDUAL);

		return aiTask;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public AITask deleteAITask(long aiTaskId) throws PortalException {
		AITask aiTask = aiTaskPersistence.findByPrimaryKey(aiTaskId);

		return deleteAITask(aiTask);
	}

	@Override
	public void deleteCompanyAITasks(long companyId) throws PortalException {
		List<AITask> aiTasks = aiTaskPersistence.findByCompanyId(companyId);

		for (AITask aiTask : aiTasks) {
			aiTaskLocalService.deleteAITask(aiTask);
		}
	}

	@Override
	public List<AITask> getAITasks(long companyId, boolean readOnly) {
		return aiTaskPersistence.findByC_R(companyId, readOnly);
	}

	@Override
	public int getAITasksCount(long companyId, boolean readOnly) {
		return aiTaskPersistence.countByC_R(companyId, readOnly);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AITask updateAITask(
			String configurationJSON, Map<Locale, String> descriptionMap,
			boolean enabled, String externalReferenceCode, long aiTaskId,
			String schemaVersion, ServiceContext serviceContext,
			Map<Locale, String> titleMap)
		throws PortalException {

		_validate(titleMap, serviceContext);

		AITask aiTask = aiTaskPersistence.findByPrimaryKey(aiTaskId);

		aiTask.setExternalReferenceCode(externalReferenceCode);
		aiTask.setConfigurationJSON(configurationJSON);
		aiTask.setDescriptionMap(descriptionMap);
		aiTask.setEnabled(enabled);
		aiTask.setTitleMap(titleMap);
		aiTask.setVersion(
			String.format(
				"%.1f", GetterUtil.getFloat(aiTask.getVersion(), 0.9F) + 0.1));

		return updateAITask(aiTask);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AITask updateStatus(
			long aiTaskId, ServiceContext serviceContext, int status,
			long userId)
		throws PortalException {

		AITask aiTask = aiTaskPersistence.findByPrimaryKey(aiTaskId);

		if (aiTask.getStatus() == status) {
			return aiTask;
		}

		User user = _userLocalService.getUser(userId);

		aiTask.setStatus(status);
		aiTask.setStatusByUserId(user.getUserId());
		aiTask.setStatusByUserName(user.getFullName());
		aiTask.setStatusDate(serviceContext.getModifiedDate(null));

		return aiTaskPersistence.update(aiTask);
	}

	private void _validate(
			Map<Locale, String> titleMap, ServiceContext serviceContext)
		throws AITaskTitleException {

		if (!GetterUtil.getBoolean(
				serviceContext.getAttribute(
					AITaskLocalServiceImpl.class.getName() + "#_validate"),
				true)) {

			return;
		}

		if (MapUtil.isEmpty(titleMap)) {
			throw new AITaskTitleException("Title is empty");
		}
	}

	@Reference
	private ResourceLocalService _resourceLocalService;

	@Reference
	private UserLocalService _userLocalService;

}