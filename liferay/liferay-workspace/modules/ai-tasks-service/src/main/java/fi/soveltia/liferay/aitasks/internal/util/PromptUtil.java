package fi.soveltia.liferay.aitasks.internal.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import dev.langchain4j.data.message.AudioContent;
import dev.langchain4j.data.message.Content;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.PdfFileContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.message.VideoContent;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import fi.soveltia.liferay.aitasks.task.context.AITaskContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Petteri Karttunen
 */
public class PromptUtil {

	public static String getSystemMessage(
		AITaskContext aiTaskContext, Map<String, Object> input,
		JSONObject jsonObject) {

		PromptTemplate promptTemplate = _getPromptTemplate(
			"systemMessage", jsonObject);

		if (promptTemplate == null) {
			return StringPool.BLANK;
		}

		Prompt prompt = _applyPromptTemplateVariables(
			aiTaskContext, input, promptTemplate);

		return prompt.text();
	}

	public static String getUserMessage(
		AITaskContext aiTaskContext, Map<String, Object> input,
		JSONObject jsonObject) {

		JSONObject inputParametersJSONObject = jsonObject.getJSONObject(
			"inputParameters");

		if (inputParametersJSONObject == null) {
			return _getPrompt(
				aiTaskContext, input,
				_getPromptTemplate("promptTemplate", jsonObject),
				MapUtil.getString(input, "text"));
		}

		List<Content> contents = new ArrayList<>();

		for (String key : inputParametersJSONObject.keySet()) {
			JSONObject inputParameterJSONObject =
				inputParametersJSONObject.getJSONObject(key);

			String type = inputParameterJSONObject.getString("type");

			String value = MapUtil.getString(input, key);

			if (Validator.isBlank(value) || Validator.isBlank(type)) {
				continue;
			}

			if (StringUtil.equals(type, "audio")) {
				contents.add(AudioContent.from(value));
			}
			else if (StringUtil.equals(type, "image")) {
				contents.add(ImageContent.from(value));
			}
			else if (StringUtil.equals(type, "pdf")) {
				contents.add(PdfFileContent.from(value));
			}
			else if (StringUtil.equals(type, "text")) {
				contents.add(
					TextContent.from(
						_getPrompt(
							aiTaskContext, input,
							_getPromptTemplate("promptTemplate", jsonObject),
							value)));
			}
			else if (StringUtil.equals(type, "video")) {
				contents.add(VideoContent.from(value));
			}
		}

		UserMessage userMessage = UserMessage.from(
			contents.toArray(new Content[0]));

		if (userMessage.hasSingleText()) {
			return userMessage.singleText();
		}

		// TODO: implement when AIServices support multimodality
		// See https://github.com/langchain4j/langchain4j/issues/938

		return userMessage.singleText();
	}

	public static String getUserMessageString(
		AITaskContext aiTaskContext, Map<String, Object> input,
		JSONObject jsonObject) {

		PromptTemplate promptTemplate = _getPromptTemplate(
			"promptTemplate", jsonObject);

		if (promptTemplate == null) {
			return MapUtil.getString(
				input, jsonObject.getString("inputParameterName", "text"));
		}

		Prompt prompt = _applyPromptTemplateVariables(
			aiTaskContext, input, promptTemplate);

		return prompt.text();
	}

	private static Prompt _applyPromptTemplateVariables(
		AITaskContext aiTaskContext, Map<String, Object> input,
		PromptTemplate promptTemplate) {

		Map<String, Object> promptTemplateVariables = new HashMap<>();

		MapUtil.isNotEmptyForEach(
			aiTaskContext.getAITaskContextParameters(),
			(key, value) -> promptTemplateVariables.put(
				"taskContext." + key, value.getStringValue()));

		MapUtil.isNotEmptyForEach(
			input,
			(key, value) -> {
				if (key.equals("history") && (value != null)) {
					promptTemplateVariables.put(
						"input." + key,
						_chatHistoryToString((List<Map<String, String>>)value));
				}
				else {
					promptTemplateVariables.put("input." + key, value);
				}
			});

		_ensurePromptTemplateVariables(promptTemplate, promptTemplateVariables);

		return promptTemplate.apply(promptTemplateVariables);
	}

	private static String _chatHistoryToString(
		List<Map<String, String>> messages) {

		StringBundler sb = new StringBundler();

		for (Map<String, String> message : messages) {
			sb.append(message.get("role"));
			sb.append(message.get(": "));
			sb.append(message.get("text"));
			sb.append("\n");
		}

		return sb.toString();
	}

	private static void _ensurePromptTemplateVariables(
		PromptTemplate promptTemplate,
		Map<String, Object> promptTemplateVariables) {

		String[] values = StringUtils.substringsBetween(
			promptTemplate.template(), "{{", "}}");

		if (ArrayUtil.isEmpty(values)) {
			return;
		}

		for (String value : values) {
			if (!promptTemplateVariables.containsKey(value)) {
				promptTemplateVariables.put(value, StringPool.BLANK);
			}
		}
	}

	private static String _getPrompt(
		AITaskContext aiTaskContext, Map<String, Object> input,
		PromptTemplate promptTemplate, String value) {

		if (promptTemplate == null) {
			return value;
		}

		Prompt prompt = _applyPromptTemplateVariables(
			aiTaskContext, input, promptTemplate);

		return prompt.text();
	}

	private static PromptTemplate _getPromptTemplate(
		String field, JSONObject jsonObject) {

		String promptTemplateString = jsonObject.getString(field);

		if (Validator.isBlank(promptTemplateString)) {
			return null;
		}

		return PromptTemplate.from(promptTemplateString);
	}

}