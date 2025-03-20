/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import fi.soveltia.liferay.aitasks.service.AITaskServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>AITaskServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AITaskServiceHttp {

	public static fi.soveltia.liferay.aitasks.model.AITask addAITask(
			HttpPrincipal httpPrincipal, String configurationJSON,
			java.util.Map<java.util.Locale, String> descriptionMap,
			boolean enabled, String externalReferenceCode, boolean readOnly,
			String schemaVersion,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			java.util.Map<java.util.Locale, String> titleMap)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AITaskServiceUtil.class, "addAITask",
				_addAITaskParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, configurationJSON, descriptionMap, enabled,
				externalReferenceCode, readOnly, schemaVersion, serviceContext,
				titleMap);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (fi.soveltia.liferay.aitasks.model.AITask)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static fi.soveltia.liferay.aitasks.model.AITask deleteAITask(
			HttpPrincipal httpPrincipal, long aiTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AITaskServiceUtil.class, "deleteAITask",
				_deleteAITaskParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, aiTaskId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (fi.soveltia.liferay.aitasks.model.AITask)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static fi.soveltia.liferay.aitasks.model.AITask fetchAITask(
			HttpPrincipal httpPrincipal, long aiTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AITaskServiceUtil.class, "fetchAITask",
				_fetchAITaskParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, aiTaskId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (fi.soveltia.liferay.aitasks.model.AITask)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static fi.soveltia.liferay.aitasks.model.AITask
			fetchAITaskByExternalReferenceCode(
				HttpPrincipal httpPrincipal, String externalReferenceCode,
				long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AITaskServiceUtil.class, "fetchAITaskByExternalReferenceCode",
				_fetchAITaskByExternalReferenceCodeParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, externalReferenceCode, companyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (fi.soveltia.liferay.aitasks.model.AITask)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static fi.soveltia.liferay.aitasks.model.AITask getAITask(
			HttpPrincipal httpPrincipal, long aiTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AITaskServiceUtil.class, "getAITask",
				_getAITaskParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, aiTaskId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (fi.soveltia.liferay.aitasks.model.AITask)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static fi.soveltia.liferay.aitasks.model.AITask
			getAITaskByExternalReferenceCode(
				HttpPrincipal httpPrincipal, long companyId,
				String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AITaskServiceUtil.class, "getAITaskByExternalReferenceCode",
				_getAITaskByExternalReferenceCodeParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, externalReferenceCode);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (fi.soveltia.liferay.aitasks.model.AITask)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static fi.soveltia.liferay.aitasks.model.AITask updateAITask(
			HttpPrincipal httpPrincipal, String configurationJSON,
			java.util.Map<java.util.Locale, String> descriptionMap,
			boolean enabled, String externalReferenceCode, long aiTaskId,
			String schemaVersion,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			java.util.Map<java.util.Locale, String> titleMap)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AITaskServiceUtil.class, "updateAITask",
				_updateAITaskParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, configurationJSON, descriptionMap, enabled,
				externalReferenceCode, aiTaskId, schemaVersion, serviceContext,
				titleMap);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (fi.soveltia.liferay.aitasks.model.AITask)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AITaskServiceHttp.class);

	private static final Class<?>[] _addAITaskParameterTypes0 = new Class[] {
		String.class, java.util.Map.class, boolean.class, String.class,
		boolean.class, String.class,
		com.liferay.portal.kernel.service.ServiceContext.class,
		java.util.Map.class
	};
	private static final Class<?>[] _deleteAITaskParameterTypes1 = new Class[] {
		long.class
	};
	private static final Class<?>[] _fetchAITaskParameterTypes2 = new Class[] {
		long.class
	};
	private static final Class<?>[]
		_fetchAITaskByExternalReferenceCodeParameterTypes3 = new Class[] {
			String.class, long.class
		};
	private static final Class<?>[] _getAITaskParameterTypes4 = new Class[] {
		long.class
	};
	private static final Class<?>[]
		_getAITaskByExternalReferenceCodeParameterTypes5 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _updateAITaskParameterTypes6 = new Class[] {
		String.class, java.util.Map.class, boolean.class, String.class,
		long.class, String.class,
		com.liferay.portal.kernel.service.ServiceContext.class,
		java.util.Map.class
	};

}