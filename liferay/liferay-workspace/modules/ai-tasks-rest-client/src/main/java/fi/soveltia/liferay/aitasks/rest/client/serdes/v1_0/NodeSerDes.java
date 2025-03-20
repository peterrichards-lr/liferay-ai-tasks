package fi.soveltia.liferay.aitasks.rest.client.serdes.v1_0;

import fi.soveltia.liferay.aitasks.rest.client.dto.v1_0.Node;
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
public class NodeSerDes {

	public static Node toDTO(String json) {
		NodeJSONParser nodeJSONParser = new NodeJSONParser();

		return nodeJSONParser.parseToDTO(json);
	}

	public static Node[] toDTOs(String json) {
		NodeJSONParser nodeJSONParser = new NodeJSONParser();

		return nodeJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Node node) {
		if (node == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (node.getCondition() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"condition\": ");

			sb.append(String.valueOf(node.getCondition()));
		}

		if (node.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append("\"");

			sb.append(_escape(node.getId()));

			sb.append("\"");
		}

		if (node.getLabel() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"label\": ");

			sb.append("\"");

			sb.append(_escape(node.getLabel()));

			sb.append("\"");
		}

		if (node.getParameters() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"parameters\": ");

			if (node.getParameters() instanceof String) {
				sb.append("\"");
				sb.append((String)node.getParameters());
				sb.append("\"");
			}
			else {
				sb.append(node.getParameters());
			}
		}

		if (node.getType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(_escape(node.getType()));

			sb.append("\"");
		}

		if (node.getUiConfiguration() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"uiConfiguration\": ");

			if (node.getUiConfiguration() instanceof String) {
				sb.append("\"");
				sb.append((String)node.getUiConfiguration());
				sb.append("\"");
			}
			else {
				sb.append(node.getUiConfiguration());
			}
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		NodeJSONParser nodeJSONParser = new NodeJSONParser();

		return nodeJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Node node) {
		if (node == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (node.getCondition() == null) {
			map.put("condition", null);
		}
		else {
			map.put("condition", String.valueOf(node.getCondition()));
		}

		if (node.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(node.getId()));
		}

		if (node.getLabel() == null) {
			map.put("label", null);
		}
		else {
			map.put("label", String.valueOf(node.getLabel()));
		}

		if (node.getParameters() == null) {
			map.put("parameters", null);
		}
		else {
			map.put("parameters", String.valueOf(node.getParameters()));
		}

		if (node.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(node.getType()));
		}

		if (node.getUiConfiguration() == null) {
			map.put("uiConfiguration", null);
		}
		else {
			map.put(
				"uiConfiguration", String.valueOf(node.getUiConfiguration()));
		}

		return map;
	}

	public static class NodeJSONParser extends BaseJSONParser<Node> {

		@Override
		protected Node createDTO() {
			return new Node();
		}

		@Override
		protected Node[] createDTOArray(int size) {
			return new Node[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "condition")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "label")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "parameters")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "uiConfiguration")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			Node node, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "condition")) {
				if (jsonParserFieldValue != null) {
					node.setCondition(
						ConditionSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					node.setId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "label")) {
				if (jsonParserFieldValue != null) {
					node.setLabel((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "parameters")) {
				if (jsonParserFieldValue != null) {
					node.setParameters((Object)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					node.setType((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "uiConfiguration")) {
				if (jsonParserFieldValue != null) {
					node.setUiConfiguration((Object)jsonParserFieldValue);
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