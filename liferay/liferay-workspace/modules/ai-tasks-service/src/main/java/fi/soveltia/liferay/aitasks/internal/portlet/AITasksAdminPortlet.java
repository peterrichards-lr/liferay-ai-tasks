package fi.soveltia.liferay.aitasks.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import fi.soveltia.liferay.aitasks.constants.AITasksPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * Dummy portlet to initialize the model permissions
 *
 *  @author Petteri Karttunen
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"javax.portlet.name=" + AITasksPortletKeys.AI_TASKS_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class AITasksAdminPortlet extends MVCPortlet {
}