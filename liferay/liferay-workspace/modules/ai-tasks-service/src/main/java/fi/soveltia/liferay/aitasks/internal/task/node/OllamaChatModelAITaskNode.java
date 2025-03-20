
package fi.soveltia.liferay.aitasks.internal.task.node;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.ollama.OllamaChatModel;

import fi.soveltia.liferay.aitasks.internal.util.SetterUtil;
import fi.soveltia.liferay.aitasks.spi.task.node.AITaskNode;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeInformation;

import java.time.Duration;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "ai.task.node.type=ollamaChatModel", service = AITaskNode.class
)
public class OllamaChatModelAITaskNode
	extends BaseChatModelAITaskNode implements AITaskNode {

	@Override
	public AITaskNodeInformation getAITaskNodeInformation() {
		return new AITaskNodeInformation("ollamaChatModel", "input");
	}

	protected ChatLanguageModel getChatLanguageModel(JSONObject jsonObject) {
		OllamaChatModel.OllamaChatModelBuilder builder =
			OllamaChatModel.builder();

		SetterUtil.setNotBlankString(
			builder::baseUrl, jsonObject.getString("baseUrl"));

		if (jsonObject.has("chatModelListeners")) {
			builder.listeners(
				getChatModelListeners(
					jsonObject.getJSONArray("chatModelListeners")));
		}

		if (jsonObject.has("customHeaders")) {
			builder.customHeaders(
				JSONUtil.toStringMap(
					jsonObject.getJSONObject("customHeaders")));
		}

		SetterUtil.setNotBlankString(
			builder::format, jsonObject.getString("format"));

		builder.logRequests(jsonObject.getBoolean("logRequests"));
		builder.logResponses(jsonObject.getBoolean("logResponses"));

		SetterUtil.setNotNullInteger(
			builder::maxRetries, jsonObject, "maxRetries");
		SetterUtil.setNotBlankString(
			builder::modelName, jsonObject.getString("modelName"));
		SetterUtil.setNotNullInteger(builder::numCtx, jsonObject, "numCtx");
		SetterUtil.setNotNullInteger(
			builder::numPredict, jsonObject, "numPredict");

		SetterUtil.setNotNullDouble(
			builder::repeatPenalty, jsonObject, "repeatPenalty");

		String responseFormat = jsonObject.getString("responseFormat");

		if (responseFormat != null) {
			if (responseFormat.equalsIgnoreCase("json")) {
				builder.responseFormat(ResponseFormat.JSON);
			}
			else {
				builder.responseFormat(ResponseFormat.TEXT);
			}
		}

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

		SetterUtil.setNotNullInteger(builder::topK, jsonObject, "topK");
		SetterUtil.setNotNullDouble(builder::topP, jsonObject, "topP");

		return builder.build();
	}

}