package fi.soveltia.liferay.aitasks.internal.task.node;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;

import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;

import fi.soveltia.liferay.aitasks.internal.util.SetterUtil;
import fi.soveltia.liferay.aitasks.spi.task.node.AITaskNode;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeInformation;

import java.time.Duration;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "ai.task.node.type=anthropicChatModel",
	service = AITaskNode.class
)
public class AnthropicChatModelAITaskNode
	extends BaseChatModelAITaskNode implements AITaskNode {

	@Override
	public AITaskNodeInformation getAITaskNodeInformation() {
		return new AITaskNodeInformation("anthropicChatModel", "input");
	}

	protected ChatLanguageModel getChatLanguageModel(JSONObject jsonObject) {
		AnthropicChatModel.AnthropicChatModelBuilder builder =
			AnthropicChatModel.builder();

		SetterUtil.setNotBlankString(
			builder::apiKey, jsonObject.getString("apiKey"));
		SetterUtil.setNotBlankString(
			builder::baseUrl, jsonObject.getString("baseUrl"));
		SetterUtil.setNotBlankString(
			builder::beta, jsonObject.getString("beta"));
		SetterUtil.setNotNullBoolean(
			builder::cacheSystemMessages, jsonObject, "cacheSystemMessages");
		SetterUtil.setNotNullBoolean(
			builder::cacheTools, jsonObject, "cacheTools");

		if (jsonObject.has("chatModelListeners")) {
			builder.listeners(
				getChatModelListeners(
					jsonObject.getJSONArray("chatModelListeners")));
		}

		builder.logRequests(jsonObject.getBoolean("logRequests"));
		builder.logResponses(jsonObject.getBoolean("logResponses"));

		SetterUtil.setNotNullInteger(
			builder::maxRetries, jsonObject, "maxRetries");
		SetterUtil.setNotNullInteger(
			builder::maxTokens, jsonObject, "maxTokens");
		SetterUtil.setNotBlankString(
			builder::modelName, jsonObject.getString("modelName"));

		if (jsonObject.has("stop")) {
			builder.stopSequences(
				JSONUtil.toStringList(jsonObject.getJSONArray("stop")));
		}

		SetterUtil.setNotNullDouble(
			builder::temperature, jsonObject, "temperature");

		if (jsonObject.has("timeout")) {
			builder.timeout(Duration.ofSeconds(jsonObject.getInt("timeout")));
		}

		SetterUtil.setNotNullInteger(builder::topK, jsonObject, "topK");
		SetterUtil.setNotNullDouble(builder::topP, jsonObject, "topP");
		SetterUtil.setNotBlankString(
			builder::version, jsonObject.getString("version"));

		return builder.build();
	}

}