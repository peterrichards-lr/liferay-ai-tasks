package fi.soveltia.liferay.aitasks.internal.task.node;

import com.liferay.portal.kernel.json.JSONObject;

import dev.langchain4j.model.image.ImageModel;

import fi.soveltia.liferay.aitasks.internal.task.util.ImageModelUtil;
import fi.soveltia.liferay.aitasks.spi.task.node.AITaskNode;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeInformation;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "ai.task.node.type=openAIImageModel", service = AITaskNode.class
)
public class OpenAIImageModelAITaskNode
	extends BaseImageModelAITaskNode implements AITaskNode {

	@Override
	public AITaskNodeInformation getAITaskNodeInformation() {
		return new AITaskNodeInformation("openAIImageModel", "input");
	}

	protected ImageModel getImageModel(JSONObject jsonObject) {
		return ImageModelUtil.getOpenAIImageModel(jsonObject);
	}

}