package fi.soveltia.liferay.aitasks.rest.internal.task.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import fi.soveltia.liferay.aitasks.spi.task.context.AITaskContextContributor;
import fi.soveltia.liferay.aitasks.task.context.AITaskContext;
import fi.soveltia.liferay.aitasks.task.context.AITaskContextParameter;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "ai.task.context.contributor.name=user",
	service = AITaskContextContributor.class
)
public class UserAITaskContextContributor implements AITaskContextContributor {

	@Override
	public void contribute(
		AITaskContext aiTaskContext, HttpServletRequest httpServletRequest,
		Locale locale, User user) {

		if (user.isGuestUser()) {
			aiTaskContext.addAITaskContextParameter(
				new AITaskContextParameter(
					"current-user-is-logged-in",
					String.valueOf(!user.isGuestUser()), !user.isGuestUser()),
				"userIsLoggedIn");
		}

		try {
			aiTaskContext.addAITaskContextParameter(
				new AITaskContextParameter(
					"current-user-birthday", String.valueOf(user.getBirthday()),
					user.getBirthday()),
				"userBirthday");
			aiTaskContext.addAITaskContextParameter(
				new AITaskContextParameter(
					"current-user-first-name", user.getFirstName()),
				"userFirstName");
			aiTaskContext.addAITaskContextParameter(
				new AITaskContextParameter(
					"current-user-full-name", user.getFullName()),
				"userFullName");
			aiTaskContext.addAITaskContextParameter(
				new AITaskContextParameter(
					"current-user-is-female", String.valueOf(user.isFemale()),
					user.isFemale()),
				"userIsFemale");

			boolean genderX = false;

			if (!user.isFemale() && !user.isMale()) {
				genderX = true;
			}

			aiTaskContext.addAITaskContextParameter(
				new AITaskContextParameter(
					"current-user-is-gender-x", String.valueOf(genderX),
					genderX),
				"userIsGenderX");
			aiTaskContext.addAITaskContextParameter(
				new AITaskContextParameter(
					"current-user-is-male", String.valueOf(user.isMale()),
					user.isMale()),
				"userIsMale");
			aiTaskContext.addAITaskContextParameter(
				new AITaskContextParameter(
					"current-user-job-title-name", user.getJobTitle()),
				"userJobTitle");

			Locale userLocale = user.getLocale();

			aiTaskContext.addAITaskContextParameter(
				new AITaskContextParameter(
					"current-user-language", userLocale.getDisplayLanguage()),
				"userLanguage");

			aiTaskContext.addAITaskContextParameter(
				new AITaskContextParameter(
					"current-user-last-name", user.getLastName()),
				"userLastName");

			aiTaskContext.addAITaskContextParameter(
				new AITaskContextParameter(
					"current-user-time-zone",
					user.getTimeZone(
					).getDisplayName(),
					user.getTimeZone()),
				"userTimeZone");
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

}