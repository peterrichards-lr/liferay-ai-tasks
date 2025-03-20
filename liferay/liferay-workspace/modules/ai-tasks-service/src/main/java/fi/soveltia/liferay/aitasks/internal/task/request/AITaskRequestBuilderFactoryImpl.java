package fi.soveltia.liferay.aitasks.internal.task.request;

import fi.soveltia.liferay.aitasks.task.request.AITaskRequestBuilder;
import fi.soveltia.liferay.aitasks.task.request.AITaskRequestBuilderFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(service = AITaskRequestBuilderFactory.class)
public class AITaskRequestBuilderFactoryImpl
	implements AITaskRequestBuilderFactory {

	@Override
	public AITaskRequestBuilder builder() {
		return new AITaskRequestBuilderImpl();
	}

}