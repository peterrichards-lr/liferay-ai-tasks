

package fi.soveltia.liferay.aitasks.internal.task.response;

import fi.soveltia.liferay.aitasks.task.response.AITaskResponseBuilder;
import fi.soveltia.liferay.aitasks.task.response.AITaskResponseBuilderFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(service = AITaskResponseBuilderFactory.class)
public class AITaskResponseBuilderFactoryImpl
	implements AITaskResponseBuilderFactory {

	@Override
	public AITaskResponseBuilder builder() {
		return new AITaskResponseBuilderImpl();
	}

}