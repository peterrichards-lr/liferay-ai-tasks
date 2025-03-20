package fi.soveltia.liferay.aitasks.internal.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import fi.soveltia.liferay.aitasks.constants.AITaskConstants;
import fi.soveltia.liferay.aitasks.model.AITask;
import fi.soveltia.liferay.aitasks.service.AITaskLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "model.class.name=fi.soveltia.liferay.aitasks.model.AITask",
	service = ModelResourcePermission.class
)
public class AITaskModelResourcePermission
	implements ModelResourcePermission<AITask> {

	@Override
	public void check(
			PermissionChecker permissionChecker, AITask aiTask, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, aiTask, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, AITask.class.getName(),
				aiTask.getPrimaryKey(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long aiTaskId, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, aiTaskId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, AITask.class.getName(), aiTaskId, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, AITask aiTask, String actionId)
		throws PortalException {

		if (permissionChecker.hasOwnerPermission(
				permissionChecker.getCompanyId(), AITask.class.getName(),
				aiTask.getAITaskId(), aiTask.getUserId(), actionId) ||
			(permissionChecker.getUserId() == aiTask.getUserId()) ||
			permissionChecker.hasPermission(
				null, AITask.class.getName(), aiTask.getPrimaryKey(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long aiTaskId, String actionId)
		throws PortalException {

		return contains(
			permissionChecker, _aiTaskLocalService.getAITask(aiTaskId),
			actionId);
	}

	@Override
	public String getModelName() {
		return AITask.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return _portletResourcePermission;
	}

	@Reference
	private AITaskLocalService _aiTaskLocalService;

	@Reference(target = "(resource.name=" + AITaskConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

}