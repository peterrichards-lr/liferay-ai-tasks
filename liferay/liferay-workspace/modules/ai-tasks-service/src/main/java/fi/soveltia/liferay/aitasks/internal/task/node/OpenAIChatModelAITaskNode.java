package fi.soveltia.liferay.aitasks.internal.task.node;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import fi.soveltia.liferay.aitasks.internal.util.SetterUtil;
import fi.soveltia.liferay.aitasks.spi.task.node.AITaskNode;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeInformation;

import java.time.Duration;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "ai.task.node.type=openAIChatModel", service = AITaskNode.class
)
public class OpenAIChatModelAITaskNode
	extends BaseChatModelAITaskNode implements AITaskNode {

	@Override
	public AITaskNodeInformation getAITaskNodeInformation() {
		return new AITaskNodeInformation("openAIChatModel", "input");
	}

	protected ChatLanguageModel getChatLanguageModel(JSONObject jsonObject) {
		OpenAiChatModel.OpenAiChatModelBuilder builder =
			OpenAiChatModel.builder();

		SetterUtil.setNotBlankString(
			builder::apiKey, jsonObject.getString("apiKey"));
		SetterUtil.setNotBlankString(
			builder::baseUrl, jsonObject.getString("baseUrl"));

		if (jsonObject.has("customHeaders")) {
			builder.customHeaders(
				JSONUtil.toStringMap(
					jsonObject.getJSONObject("customHeaders")));
		}

		SetterUtil.setNotNullDouble(
			builder::frequencyPenalty, jsonObject, "frequencyPenalty");

		if (jsonObject.has("chatModelListeners")) {
			builder.listeners(
				getChatModelListeners(
					jsonObject.getJSONArray("chatModelListeners")));
		}

		if (jsonObject.has("logitBias")) {
			builder.logitBias(
				_toLogitBias(jsonObject.getJSONObject("logitBias")));
		}

		builder.logRequests(jsonObject.getBoolean("logRequests"));
		builder.logResponses(jsonObject.getBoolean("logResponses"));

		SetterUtil.setNotNullInteger(
			builder::maxRetries, jsonObject, "maxRetries");
		SetterUtil.setNotNullInteger(
			builder::maxTokens, jsonObject, "maxTokens");
		SetterUtil.setNotBlankString(
			builder::modelName, jsonObject.getString("modelName"));
		SetterUtil.setNotBlankString(
			builder::organizationId, jsonObject.getString("organizationId"));
		SetterUtil.setNotNullDouble(
			builder::presencePenalty, jsonObject, "presencePenalty");
		SetterUtil.setNotBlankString(
			builder::responseFormat, jsonObject.getString("responseFormat"));
		SetterUtil.setNotNullInteger(builder::seed, jsonObject, "seed");

		if (jsonObject.has("stop")) {
			builder.stop(
				JSONUtil.toStringList(jsonObject.getJSONArray("stop")));
		}

		SetterUtil.setNotNullDouble(
			builder::temperature, jsonObject, "temperature");

		if (jsonObject.has("timeout")) {
			builder.timeout(Duration.ofSeconds(jsonObject.getInt("timeout")));
		}

		SetterUtil.setNotNullDouble(builder::topP, jsonObject, "topP");
		SetterUtil.setNotBlankString(
			builder::user, jsonObject.getString("user"));

		return builder.build();
	}

	private Map<String, Integer> _toLogitBias(JSONObject jsonObject) {
		Map<String, Integer> map = new HashMap<>();

		for (String key : jsonObject.keySet()) {
			map.put(key, jsonObject.getInt(key));
		}

		return map;
	}

}