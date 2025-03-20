package fi.soveltia.liferay.aitasks.rest.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false", "osgi.jaxrs.application.base=/ai-tasks",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=AiTasksRest"
	},
	service = Application.class
)
@Generated("")
public class AiTasksRestApplication extends Application {
}