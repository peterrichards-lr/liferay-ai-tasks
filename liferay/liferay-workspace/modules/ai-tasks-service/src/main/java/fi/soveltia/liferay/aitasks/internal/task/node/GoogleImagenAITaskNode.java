package fi.soveltia.liferay.aitasks.internal.task.node;

import com.liferay.portal.kernel.json.JSONObject;

import dev.langchain4j.model.vertexai.VertexAiImageModel;

import fi.soveltia.liferay.aitasks.internal.task.util.ImageModelUtil;
import fi.soveltia.liferay.aitasks.spi.task.node.AITaskNode;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeInformation;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "ai.task.node.type=googleImagen", service = AITaskNode.class
)
public class GoogleImagenAITaskNode
	extends BaseImageModelAITaskNode implements AITaskNode {

	@Override
	public AITaskNodeInformation getAITaskNodeInformation() {
		return new AITaskNodeInformation("googleImagen", "input");
	}

	protected VertexAiImageModel getImageModel(JSONObject jsonObject) {
		return ImageModelUtil.getVertexAiImageModel(jsonObject);
	}

}