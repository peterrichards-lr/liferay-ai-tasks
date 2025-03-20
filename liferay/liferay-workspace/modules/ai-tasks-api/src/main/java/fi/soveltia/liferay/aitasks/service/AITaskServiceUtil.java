/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;

import fi.soveltia.liferay.aitasks.model.AITask;

import java.util.Map;

/**
 * Provides the remote service utility for AITask. This utility wraps
 * <code>fi.soveltia.liferay.aitasks.service.impl.AITaskServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AITaskService
 * @generated
 */
public class AITaskServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>fi.soveltia.liferay.aitasks.service.impl.AITaskServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static AITask addAITask(
			String configurationJSON,
			Map<java.util.Locale, String> descriptionMap, boolean enabled,
			String externalReferenceCode, boolean readOnly,
			String schemaVersion,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			Map<java.util.Locale, String> titleMap)
		throws PortalException {

		return getService().addAITask(
			configurationJSON, descriptionMap, enabled, externalReferenceCode,
			readOnly, schemaVersion, serviceContext, titleMap);
	}

	public static AITask deleteAITask(long aiTaskId) throws PortalException {
		return getService().deleteAITask(aiTaskId);
	}

	public static AITask fetchAITask(long aiTaskId) throws PortalException {
		return getService().fetchAITask(aiTaskId);
	}

	public static AITask fetchAITaskByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException {

		return getService().fetchAITaskByExternalReferenceCode(
			externalReferenceCode, companyId);
	}

	public static AITask getAITask(long aiTaskId) throws PortalException {
		return getService().getAITask(aiTaskId);
	}

	public static AITask getAITaskByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		return getService().getAITaskByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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

	public static AITaskService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<AITaskService> _serviceSnapshot =
		new Snapshot<>(AITaskServiceUtil.class, AITaskService.class);

}