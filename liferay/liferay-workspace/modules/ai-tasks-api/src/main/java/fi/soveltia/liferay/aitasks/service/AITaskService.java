/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import fi.soveltia.liferay.aitasks.model.AITask;

import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for AITask. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AITaskServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface AITaskService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>fi.soveltia.liferay.aitasks.service.impl.AITaskServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the ai task remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link AITaskServiceUtil} if injection and service tracking are not available.
	 */
	public AITask addAITask(
			String configurationJSON, Map<Locale, String> descriptionMap,
			boolean enabled, String externalReferenceCode, boolean readOnly,
			String schemaVersion, ServiceContext serviceContext,
			Map<Locale, String> titleMap)
		throws PortalException;

	public AITask deleteAITask(long aiTaskId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AITask fetchAITask(long aiTaskId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AITask fetchAITaskByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AITask getAITask(long aiTaskId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AITask getAITaskByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public AITask updateAITask(
			String configurationJSON, Map<Locale, String> descriptionMap,
			boolean enabled, String externalReferenceCode, long aiTaskId,
			String schemaVersion, ServiceContext serviceContext,
			Map<Locale, String> titleMap)
		throws PortalException;

}