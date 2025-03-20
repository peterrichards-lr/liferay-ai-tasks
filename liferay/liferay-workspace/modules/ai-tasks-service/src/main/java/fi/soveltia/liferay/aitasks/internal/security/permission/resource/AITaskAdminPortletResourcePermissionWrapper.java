package fi.soveltia.liferay.aitasks.internal.security.permission.resource;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.BasePortletResourcePermissionWrapper;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionLogic;

import fi.soveltia.liferay.aitasks.constants.AITaskConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "resource.name=" + AITaskConstants.RESOURCE_NAME,
	service = PortletResourcePermission.class
)
public class AITaskAdminPortletResourcePermissionWrapper
	extends BasePortletResourcePermissionWrapper {

	@Override
	protected PortletResourcePermission doGetPortletResourcePermission() {
		return PortletResourcePermissionFactory.create(
			AITaskConstants.RESOURCE_NAME,
			new ListTypePortletResourcePermissionLogic());
	}

	private static class ListTypePortletResourcePermissionLogic
		implements PortletResourcePermissionLogic {

		@Override
		public Boolean contains(
			PermissionChecker permissionChecker, String name, Group group,
			String actionId) {

			if (permissionChecker.hasPermission(group, name, 0, actionId)) {
				return true;
			}

			return false;
		}

	}

}