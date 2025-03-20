
package fi.soveltia.liferay.aitasks.internal.task.node;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.vertexai.HarmCategory;
import dev.langchain4j.model.vertexai.SafetyThreshold;
import dev.langchain4j.model.vertexai.SchemaHelper;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;

import fi.soveltia.liferay.aitasks.internal.util.SetterUtil;
import fi.soveltia.liferay.aitasks.spi.task.node.AITaskNode;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeInformation;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "ai.task.node.type=geminiChatModel", service = AITaskNode.class
)
public class GeminiChatModelAITaskNode
	extends BaseChatModelAITaskNode implements AITaskNode {

	@Override
	public AITaskNodeInformation getAITaskNodeInformation() {
		return new AITaskNodeInformation("geminiChatModel", "input");
	}

	protected ChatLanguageModel getChatLanguageModel(JSONObject jsonObject) {
		VertexAiGeminiChatModel.VertexAiGeminiChatModelBuilder builder =
			VertexAiGeminiChatModel.builder();

		if (jsonObject.has("allowedFunctionNames")) {
			builder.allowedFunctionNames(
				JSONUtil.toStringList(
					jsonObject.getJSONArray("allowedFunctionNames")));
		}

		SetterUtil.setNotBlankString(
			builder::location, jsonObject.getString("location"));

		builder.logRequests(jsonObject.getBoolean("logRequests"));
		builder.logResponses(jsonObject.getBoolean("logResponses"));

		SetterUtil.setNotNullInteger(
			builder::maxOutputTokens, jsonObject, "maxOutputTokens");
		SetterUtil.setNotNullInteger(
			builder::maxRetries, jsonObject, "maxRetries");
		SetterUtil.setNotBlankString(
			builder::modelName, jsonObject.getString("modelName"));
		SetterUtil.setNotBlankString(
			builder::project, jsonObject.getString("project"));
		SetterUtil.setNotBlankString(
			builder::responseMimeType,
			jsonObject.getString("responseMimeType"));

		JSONObject responseSchemaJSONObject = jsonObject.getJSONObject(
			"responseSchema");

		if (responseSchemaJSONObject != null) {
			builder.responseSchema(
				SchemaHelper.fromJsonSchema(
					responseSchemaJSONObject.toString()));
		}

		if (jsonObject.has("safetySettings")) {
			builder.safetySettings(
				_toSafetySettings(jsonObject.getJSONObject("safetySettings")));
		}

		SetterUtil.setNotNullDoubleAsFloat(
			builder::temperature, jsonObject, "temperature");
		SetterUtil.setNotNullInteger(builder::topK, jsonObject, "topK");
		SetterUtil.setNotNullDoubleAsFloat(builder::topP, jsonObject, "topP");

		builder.useGoogleSearch(jsonObject.getBoolean("useGoogleSearch"));

		SetterUtil.setNotBlankString(
			builder::vertexSearchDatastore,
			jsonObject.getString("vertexSearchDatastore"));

		return builder.build();
	}

	private Map<HarmCategory, SafetyThreshold> _toSafetySettings(
		JSONObject safetySettingsJSONObject) {

		Map<HarmCategory, SafetyThreshold> safetySettings = new HashMap<>();

		for (String key : safetySettingsJSONObject.keySet()) {
			safetySettings.put(
				HarmCategory.valueOf(key), SafetyThreshold.valueOf(key));
		}

		return safetySettings;
	}

}