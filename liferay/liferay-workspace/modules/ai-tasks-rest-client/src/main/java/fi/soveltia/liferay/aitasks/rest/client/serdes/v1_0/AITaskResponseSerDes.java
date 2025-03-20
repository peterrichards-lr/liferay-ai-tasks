package fi.soveltia.liferay.aitasks.rest.client.serdes.v1_0;

import fi.soveltia.liferay.aitasks.rest.client.dto.v1_0.AITaskResponse;
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
public class AITaskResponseSerDes {

	public static AITaskResponse toDTO(String json) {
		AITaskResponseJSONParser aiTaskResponseJSONParser =
			new AITaskResponseJSONParser();

		return aiTaskResponseJSONParser.parseToDTO(json);
	}

	public static AITaskResponse[] toDTOs(String json) {
		AITaskResponseJSONParser aiTaskResponseJSONParser =
			new AITaskResponseJSONParser();

		return aiTaskResponseJSONParser.parseToDTOs(json);
	}

	public static String toJSON(AITaskResponse aiTaskResponse) {
		if (aiTaskResponse == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (aiTaskResponse.getDebugInfo() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"debugInfo\": ");

			if (aiTaskResponse.getDebugInfo() instanceof String) {
				sb.append("\"");
				sb.append((String)aiTaskResponse.getDebugInfo());
				sb.append("\"");
			}
			else {
				sb.append(aiTaskResponse.getDebugInfo());
			}
		}

		if (aiTaskResponse.getOutput() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"output\": ");

			if (aiTaskResponse.getOutput() instanceof String) {
				sb.append("\"");
				sb.append((String)aiTaskResponse.getOutput());
				sb.append("\"");
			}
			else {
				sb.append(aiTaskResponse.getOutput());
			}
		}

		if (aiTaskResponse.getTook() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"took\": ");

			sb.append("\"");

			sb.append(_escape(aiTaskResponse.getTook()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AITaskResponseJSONParser aiTaskResponseJSONParser =
			new AITaskResponseJSONParser();

		return aiTaskResponseJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(AITaskResponse aiTaskResponse) {
		if (aiTaskResponse == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (aiTaskResponse.getDebugInfo() == null) {
			map.put("debugInfo", null);
		}
		else {
			map.put("debugInfo", String.valueOf(aiTaskResponse.getDebugInfo()));
		}

		if (aiTaskResponse.getOutput() == null) {
			map.put("output", null);
		}
		else {
			map.put("output", String.valueOf(aiTaskResponse.getOutput()));
		}

		if (aiTaskResponse.getTook() == null) {
			map.put("took", null);
		}
		else {
			map.put("took", String.valueOf(aiTaskResponse.getTook()));
		}

		return map;
	}

	public static class AITaskResponseJSONParser
		extends BaseJSONParser<AITaskResponse> {

		@Override
		protected AITaskResponse createDTO() {
			return new AITaskResponse();
		}

		@Override
		protected AITaskResponse[] createDTOArray(int size) {
			return new AITaskResponse[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "debugInfo")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "output")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "took")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			AITaskResponse aiTaskResponse, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "debugInfo")) {
				if (jsonParserFieldValue != null) {
					aiTaskResponse.setDebugInfo((Object)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "output")) {
				if (jsonParserFieldValue != null) {
					aiTaskResponse.setOutput((Object)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "took")) {
				if (jsonParserFieldValue != null) {
					aiTaskResponse.setTook((String)jsonParserFieldValue);
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