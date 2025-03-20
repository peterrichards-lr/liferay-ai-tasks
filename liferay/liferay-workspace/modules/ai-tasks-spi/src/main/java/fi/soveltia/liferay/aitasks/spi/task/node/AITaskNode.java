package fi.soveltia.liferay.aitasks.spi.task.node;

import com.liferay.portal.kernel.json.JSONObject;

import fi.soveltia.liferay.aitasks.task.context.AITaskContext;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeInformation;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeResponse;

import java.util.Map;

/**
 * @author Petteri Karttunen
 */
public interface AITaskNode {

	public AITaskNodeResponse execute(
		AITaskContext aiTaskContext, boolean debug, String id,
		Map<String, Object> input, JSONObject jsonObject);

	public AITaskNodeInformation getAITaskNodeInformation();

}