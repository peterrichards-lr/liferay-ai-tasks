package fi.soveltia.liferay.aitasks.spi.task.context;

import com.liferay.portal.kernel.model.User;

import fi.soveltia.liferay.aitasks.task.context.AITaskContext;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Petteri Karttunen
 */
public interface AITaskContextContributor {

	public void contribute(
		AITaskContext aiTaskContext, HttpServletRequest httpServletRequest,
		Locale locale, User user);

}