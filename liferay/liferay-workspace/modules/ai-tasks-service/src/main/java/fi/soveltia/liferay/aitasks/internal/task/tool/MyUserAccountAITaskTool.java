package fi.soveltia.liferay.aitasks.internal.task.tool;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.service.UserServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

import fi.soveltia.liferay.aitasks.spi.task.tool.AITaskTool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "ai.task.tool.name=myUserAccount", service = AITaskTool.class
)
public class MyUserAccountAITaskTool implements AITaskTool {

	@Override
	public Object getExecutor(JSONObject configurationJSONObject) {
		return new Executor();
	}

	public class Executor {

		@Tool("Updates my email address")
		public User updateMyEmailAddress(
				@P("The new email address of the user") String emailAddress)
			throws PortalException {

			return _updateUser(emailAddress, null, null, null);
		}

		@Tool("Updates my first name")
		public User updateMyFirstName(
				@P("The new first name of the user") String firstName)
			throws PortalException {

			return _updateUser(null, firstName, null, null);
		}

		@Tool("Updates my job title")
		public User updateMyJobTitle(
				@P("The new job title of the user") String jobTitle)
			throws PortalException {

			return _updateUser(null, null, null, jobTitle);
		}

		@Tool("Updates my last name")
		public User updateMyLastName(
				@P("The new last name of the user") String lastName)
			throws PortalException {

			return _updateUser(null, null, lastName, null);
		}

		private User _updateUser(
				String emailAddress, String firstName, String lastName,
				String jobTitle)
			throws PortalException {

			try {
				User user = UserServiceUtil.getCurrentUser();

				if (user.isGuestUser()) {
					return null;
				}

				if (!Validator.isBlank(emailAddress)) {
					user.setFirstName(emailAddress);
				}

				if (!Validator.isBlank(firstName)) {
					user.setFirstName(firstName);
				}

				if (!Validator.isBlank(jobTitle)) {
					user.setFirstName(jobTitle);
				}

				if (!Validator.isBlank(lastName)) {
					user.setFirstName(lastName);
				}

				return UserLocalServiceUtil.updateUser(user);
			}
			catch (PortalException portalException) {
				log.error(portalException);

				throw portalException;
			}
		}

	}

	protected static final Log log = LogFactoryUtil.getLog(
		MyUserAccountAITaskTool.class);

	@Reference
	protected UserService userService;

}