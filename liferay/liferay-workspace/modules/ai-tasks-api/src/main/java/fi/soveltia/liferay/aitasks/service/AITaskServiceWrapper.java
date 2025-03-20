/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AITaskService}.
 *
 * @author Brian Wing Shun Chan
 * @see AITaskService
 * @generated
 */
public class AITaskServiceWrapper
	implements AITaskService, ServiceWrapper<AITaskService> {

	public AITaskServiceWrapper() {
		this(null);
	}

	public AITaskServiceWrapper(AITaskService aiTaskService) {
		_aiTaskService = aiTaskService;
	}

	@Override
	public fi.soveltia.liferay.aitasks.model.AITask addAITask(
			String configurationJSON,
			java.util.Map<java.util.Locale, String> descriptionMap,
			boolean enabled, String externalReferenceCode, boolean readOnly,
			String schemaVersion,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			java.util.Map<java.util.Locale, String> titleMap)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskService.addAITask(
			configurationJSON, descriptionMap, enabled, externalReferenceCode,
			readOnly, schemaVersion, serviceContext, titleMap);
	}

	@Override
	public fi.soveltia.liferay.aitasks.model.AITask deleteAITask(long aiTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskService.deleteAITask(aiTaskId);
	}

	@Override
	public fi.soveltia.liferay.aitasks.model.AITask fetchAITask(long aiTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskService.fetchAITask(aiTaskId);
	}

	@Override
	public fi.soveltia.liferay.aitasks.model.AITask
			fetchAITaskByExternalReferenceCode(
				String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskService.fetchAITaskByExternalReferenceCode(
			externalReferenceCode, companyId);
	}

	@Override
	public fi.soveltia.liferay.aitasks.model.AITask getAITask(long aiTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskService.getAITask(aiTaskId);
	}

	@Override
	public fi.soveltia.liferay.aitasks.model.AITask
			getAITaskByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _aiTaskService.getAITaskByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _aiTaskService.getOSGiServiceIdentifier();
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

		return _aiTaskService.updateAITask(
			configurationJSON, descriptionMap, enabled, externalReferenceCode,
			aiTaskId, schemaVersion, serviceContext, titleMap);
	}

	@Override
	public AITaskService getWrappedService() {
		return _aiTaskService;
	}

	@Override
	public void setWrappedService(AITaskService aiTaskService) {
		_aiTaskService = aiTaskService;
	}

	private AITaskService _aiTaskService;

}