package fi.soveltia.liferay.aitasks.rest.internal.task.context;

import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.User;

import fi.soveltia.liferay.aitasks.spi.task.context.AITaskContextContributor;
import fi.soveltia.liferay.aitasks.task.context.AITaskContext;
import fi.soveltia.liferay.aitasks.task.context.AITaskContextParameter;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "ai.task.context.contributor.name=context",
	service = AITaskContextContributor.class
)
public class ContextAITaskContextContributor
	implements AITaskContextContributor {

	@Override
	public void contribute(
		AITaskContext aiTaskContext, HttpServletRequest httpServletRequest,
		Locale locale, User user) {

		aiTaskContext.addAITaskContextParameter(
			new AITaskContextParameter(
				"current-language-id", _language.getLanguageId(locale)),
			"currentLanguageId");
		aiTaskContext.addAITaskContextParameter(
			new AITaskContextParameter(
				"remote-ip-address", httpServletRequest.getRemoteAddr()),
			"remoteIPAddress");
	}

	@Reference
	private Language _language;

}