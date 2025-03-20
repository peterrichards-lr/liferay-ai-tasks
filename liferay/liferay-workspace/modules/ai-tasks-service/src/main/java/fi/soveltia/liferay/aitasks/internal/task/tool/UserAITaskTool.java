package fi.soveltia.liferay.aitasks.internal.task.tool;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.service.UserServiceUtil;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.StringUtil;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

import fi.soveltia.liferay.aitasks.spi.task.tool.AITaskTool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(property = "ai.task.tool.name=user", service = AITaskTool.class)
public class UserAITaskTool implements AITaskTool {

	@Override
	public Object getExecutor(JSONObject configurationJSONObject) {
		return new Executor();
	}

	public class Executor {

		@Tool("Creates a new user")
		public User createUser(
				@P("The email address of the user") String emailAddress,
				@P("The first name of the user") String firstName,
				@P("The last name of the user") String lastName)
			throws PortalException {

			try {
				return UserServiceUtil.addUser(
					CompanyThreadLocal.getCompanyId(), true, StringPool.BLANK,
					StringPool.BLANK, true, StringUtil.randomString(),
					emailAddress, 0L, StringPool.BLANK,
					LocaleThreadLocal.getDefaultLocale(), firstName,
					StringPool.BLANK, lastName, 0L, 0L, false, 1, 1, 1970,
					"Tester", new long[0], new long[0], new long[0],
					new long[0], false,
					ServiceContextThreadLocal.getServiceContext());
			}
			catch (PortalException portalException) {
				log.error(portalException);

				throw portalException;
			}
		}

	}

	protected static final Log log = LogFactoryUtil.getLog(
		UserAITaskTool.class);

	@Reference
	protected UserService userService;

}