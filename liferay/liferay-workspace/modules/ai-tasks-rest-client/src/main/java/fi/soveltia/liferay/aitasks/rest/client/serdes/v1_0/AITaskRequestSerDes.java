package fi.soveltia.liferay.aitasks.rest.client.serdes.v1_0;

import fi.soveltia.liferay.aitasks.rest.client.dto.v1_0.AITaskRequest;
import fi.soveltia.liferay.aitasks.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Petteri Karttunen
 * @generated
 */
@Generated("")
public class AITaskRequestSerDes {

	public static AITaskRequest toDTO(String json) {
		AITaskRequestJSONParser aiTaskRequestJSONParser =
			new AITaskRequestJSONParser();

		return aiTaskRequestJSONParser.parseToDTO(json);
	}

	public static AITaskRequest[] toDTOs(String json) {
		AITaskRequestJSONParser aiTaskRequestJSONParser =
			new AITaskRequestJSONParser();

		return aiTaskRequestJSONParser.parseToDTOs(json);
	}

	public static String toJSON(AITaskRequest aiTaskRequest) {
		if (aiTaskRequest == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (aiTaskRequest.getInput() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"input\": ");

			if (aiTaskRequest.getInput() instanceof String) {
				sb.append("\"");
				sb.append((String)aiTaskRequest.getInput());
				sb.append("\"");
			}
			else {
				sb.append(aiTaskRequest.getInput());
			}
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AITaskRequestJSONParser aiTaskRequestJSONParser =
			new AITaskRequestJSONParser();

		return aiTaskRequestJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(AITaskRequest aiTaskRequest) {
		if (aiTaskRequest == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (aiTaskRequest.getInput() == null) {
			map.put("input", null);
		}
		else {
			map.put("input", String.valueOf(aiTaskRequest.getInput()));
		}

		return map;
	}

	public static class AITaskRequestJSONParser
		extends BaseJSONParser<AITaskRequest> {

		@Override
		protected AITaskRequest createDTO() {
			return new AITaskRequest();
		}

		@Override
		protected AITaskRequest[] createDTOArray(int size) {
			return new AITaskRequest[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "input")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			AITaskRequest aiTaskRequest, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "input")) {
				if (jsonParserFieldValue != null) {
					aiTaskRequest.setInput((Object)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			sb.append(_toJSON(value));

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static String _toJSON(Object value) {
		if (value instanceof Map) {
			return _toJSON((Map)value);
		}

		Class<?> clazz = value.getClass();

		if (clazz.isArray()) {
			StringBuilder sb = new StringBuilder("[");

			Object[] values = (Object[])value;

			for (int i = 0; i < values.length; i++) {
				sb.append(_toJSON(values[i]));

				if ((i + 1) < values.length) {
					sb.append(", ");
				}
			}

			sb.append("]");

			return sb.toString();
		}

		if (value instanceof String) {
			return "\"" + _escape(value) + "\"";
		}

		return String.valueOf(value);
	}

}