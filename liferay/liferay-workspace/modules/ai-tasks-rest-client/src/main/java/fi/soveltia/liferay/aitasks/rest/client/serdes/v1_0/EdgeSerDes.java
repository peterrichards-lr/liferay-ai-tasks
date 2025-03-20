package fi.soveltia.liferay.aitasks.rest.client.serdes.v1_0;

import fi.soveltia.liferay.aitasks.rest.client.dto.v1_0.Edge;
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
public class EdgeSerDes {

	public static Edge toDTO(String json) {
		EdgeJSONParser edgeJSONParser = new EdgeJSONParser();

		return edgeJSONParser.parseToDTO(json);
	}

	public static Edge[] toDTOs(String json) {
		EdgeJSONParser edgeJSONParser = new EdgeJSONParser();

		return edgeJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Edge edge) {
		if (edge == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (edge.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append("\"");

			sb.append(_escape(edge.getId()));

			sb.append("\"");
		}

		if (edge.getSource() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"source\": ");

			sb.append("\"");

			sb.append(_escape(edge.getSource()));

			sb.append("\"");
		}

		if (edge.getTarget() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"target\": ");

			sb.append("\"");

			sb.append(_escape(edge.getTarget()));

			sb.append("\"");
		}

		if (edge.getUiConfiguration() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"uiConfiguration\": ");

			if (edge.getUiConfiguration() instanceof String) {
				sb.append("\"");
				sb.append((String)edge.getUiConfiguration());
				sb.append("\"");
			}
			else {
				sb.append(edge.getUiConfiguration());
			}
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		EdgeJSONParser edgeJSONParser = new EdgeJSONParser();

		return edgeJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Edge edge) {
		if (edge == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (edge.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(edge.getId()));
		}

		if (edge.getSource() == null) {
			map.put("source", null);
		}
		else {
			map.put("source", String.valueOf(edge.getSource()));
		}

		if (edge.getTarget() == null) {
			map.put("target", null);
		}
		else {
			map.put("target", String.valueOf(edge.getTarget()));
		}

		if (edge.getUiConfiguration() == null) {
			map.put("uiConfiguration", null);
		}
		else {
			map.put(
				"uiConfiguration", String.valueOf(edge.getUiConfiguration()));
		}

		return map;
	}

	public static class EdgeJSONParser extends BaseJSONParser<Edge> {

		@Override
		protected Edge createDTO() {
			return new Edge();
		}

		@Override
		protected Edge[] createDTOArray(int size) {
			return new Edge[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "id")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "source")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "target")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "uiConfiguration")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			Edge edge, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					edge.setId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "source")) {
				if (jsonParserFieldValue != null) {
					edge.setSource((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "target")) {
				if (jsonParserFieldValue != null) {
					edge.setTarget((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "uiConfiguration")) {
				if (jsonParserFieldValue != null) {
					edge.setUiConfiguration((Object)jsonParserFieldValue);
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