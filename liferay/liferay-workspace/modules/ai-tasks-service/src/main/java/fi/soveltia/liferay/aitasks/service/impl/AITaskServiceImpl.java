/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.service.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;

import fi.soveltia.liferay.aitasks.constants.AITaskActionKeys;
import fi.soveltia.liferay.aitasks.constants.AITaskConstants;
import fi.soveltia.liferay.aitasks.exception.AITaskReadOnlyException;
import fi.soveltia.liferay.aitasks.model.AITask;
import fi.soveltia.liferay.aitasks.service.base.AITaskServiceBaseImpl;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=aitasks",
		"json.web.service.context.path=AITask"
	},
	service = AopService.class
)
public class AITaskServiceImpl extends AITaskServiceBaseImpl {

	@Override
	public AITask addAITask(
			String configurationJSON, Map<Locale, String> descriptionMap,
			boolean enabled, String externalReferenceCode, boolean readOnly,
			String schemaVersion, ServiceContext serviceContext,
			Map<Locale, String> titleMap)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), null, AITaskActionKeys.ADD_AI_TASK);

		return aiTaskLocalService.addAITask(
			configurationJSON, descriptionMap, enabled, externalReferenceCode,
			readOnly, schemaVersion, serviceContext, titleMap, getUserId());
	}

	@Override
	public AITask deleteAITask(long aiTaskId) throws PortalException {
		_aiTaskModelResourcePermission.check(
			getPermissionChecker(), aiTaskId, ActionKeys.DELETE);

		AITask aiTask = aiTaskPersistence.findByPrimaryKey(aiTaskId);

		if (aiTask.isReadOnly()) {
			throw new AITaskReadOnlyException(
				StringBundler.concat("AI Task ", aiTaskId, " is read-only"));
		}

		return aiTaskLocalService.deleteAITask(aiTaskId);
	}

	@Override
	public AITask fetchAITask(long aiTaskId) throws PortalException {
		AITask aiTask = aiTaskLocalService.fetchAITask(aiTaskId);

		if (aiTask != null) {
			_aiTaskModelResourcePermission.check(
				getPermissionChecker(), aiTask, ActionKeys.VIEW);
		}

		return aiTask;
	}

	@Override
	public AITask fetchAITaskByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException {

		AITask aiTask = aiTaskLocalService.fetchAITaskByExternalReferenceCode(
			externalReferenceCode, companyId);

		if (aiTask != null) {
			_aiTaskModelResourcePermission.check(
				getPermissionChecker(), aiTask, ActionKeys.VIEW);
		}

		return aiTask;
	}

	@Override
	public AITask getAITask(long aiTaskId) throws PortalException {
		AITask aiTask = aiTaskLocalService.getAITask(aiTaskId);

		_aiTaskModelResourcePermission.check(
			getPermissionChecker(), aiTask, AITaskActionKeys.APPLY_AI_TASK);

		return aiTask;
	}

	@Override
	public AITask getAITaskByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		AITask aiTask = aiTaskLocalService.getAITaskByExternalReferenceCode(
			externalReferenceCode, companyId);

		_aiTaskModelResourcePermission.check(
			getPermissionChecker(), aiTask, AITaskActionKeys.APPLY_AI_TASK);

		return aiTask;
	}

	@Override
	public AITask updateAITask(
			String configurationJSON, Map<Locale, String> descriptionMap,
			boolean enabled, String externalReferenceCode, long aiTaskId,
			String schemaVersion, ServiceContext serviceContext,
			Map<Locale, String> titleMap)
		throws PortalException {

		_aiTaskModelResourcePermission.check(
			getPermissionChecker(), aiTaskId, ActionKeys.UPDATE);

		AITask aiTask = aiTaskPersistence.findByPrimaryKey(aiTaskId);

		if (aiTask.isReadOnly()) {
			throw new AITaskReadOnlyException(
				StringBundler.concat("AI Task ", aiTaskId, " is read-only"));
		}

		return aiTaskLocalService.updateAITask(
			configurationJSON, descriptionMap, enabled, externalReferenceCode,
			aiTaskId, schemaVersion, serviceContext, titleMap);
	}

	@Reference(
		target = "(model.class.name=fi.soveltia.liferay.aitasks.model.AITask)"
	)
	private volatile ModelResourcePermission<AITask>
		_aiTaskModelResourcePermission;

	@Reference(target = "(resource.name=" + AITaskConstants.RESOURCE_NAME + ")")
	private volatile PortletResourcePermission _portletResourcePermission;

}