package fi.soveltia.liferay.aitasks.rest.client.serdes.v1_0;

import fi.soveltia.liferay.aitasks.rest.client.dto.v1_0.Configuration;
import fi.soveltia.liferay.aitasks.rest.client.dto.v1_0.Edge;
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
public class ConfigurationSerDes {

	public static Configuration toDTO(String json) {
		ConfigurationJSONParser configurationJSONParser =
			new ConfigurationJSONParser();

		return configurationJSONParser.parseToDTO(json);
	}

	public static Configuration[] toDTOs(String json) {
		ConfigurationJSONParser configurationJSONParser =
			new ConfigurationJSONParser();

		return configurationJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Configuration configuration) {
		if (configuration == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (configuration.getDebug() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"debug\": ");

			sb.append(configuration.getDebug());
		}

		if (configuration.getEdges() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"edges\": ");

			sb.append("[");

			for (int i = 0; i < configuration.getEdges().length; i++) {
				sb.append(String.valueOf(configuration.getEdges()[i]));

				if ((i + 1) < configuration.getEdges().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (configuration.getNodes() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"nodes\": ");

			sb.append("[");

			for (int i = 0; i < configuration.getNodes().length; i++) {
				sb.append(String.valueOf(configuration.getNodes()[i]));

				if ((i + 1) < configuration.getNodes().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (configuration.getStartNodeId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"startNodeId\": ");

			sb.append("\"");

			sb.append(_escape(configuration.getStartNodeId()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ConfigurationJSONParser configurationJSONParser =
			new ConfigurationJSONParser();

		return configurationJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Configuration configuration) {
		if (configuration == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (configuration.getDebug() == null) {
			map.put("debug", null);
		}
		else {
			map.put("debug", String.valueOf(configuration.getDebug()));
		}

		if (configuration.getEdges() == null) {
			map.put("edges", null);
		}
		else {
			map.put("edges", String.valueOf(configuration.getEdges()));
		}

		if (configuration.getNodes() == null) {
			map.put("nodes", null);
		}
		else {
			map.put("nodes", String.valueOf(configuration.getNodes()));
		}

		if (configuration.getStartNodeId() == null) {
			map.put("startNodeId", null);
		}
		else {
			map.put(
				"startNodeId", String.valueOf(configuration.getStartNodeId()));
		}

		return map;
	}

	public static class ConfigurationJSONParser
		extends BaseJSONParser<Configuration> {

		@Override
		protected Configuration createDTO() {
			return new Configuration();
		}

		@Override
		protected Configuration[] createDTOArray(int size) {
			return new Configuration[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "debug")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "edges")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "nodes")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "startNodeId")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			Configuration configuration, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "debug")) {
				if (jsonParserFieldValue != null) {
					configuration.setDebug((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "edges")) {
				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					Edge[] edgesArray = new Edge[jsonParserFieldValues.length];

					for (int i = 0; i < edgesArray.length; i++) {
						edgesArray[i] = EdgeSerDes.toDTO(
							(String)jsonParserFieldValues[i]);
					}

					configuration.setEdges(edgesArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "nodes")) {
				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					Node[] nodesArray = new Node[jsonParserFieldValues.length];

					for (int i = 0; i < nodesArray.length; i++) {
						nodesArray[i] = NodeSerDes.toDTO(
							(String)jsonParserFieldValues[i]);
					}

					configuration.setNodes(nodesArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "startNodeId")) {
				if (jsonParserFieldValue != null) {
					configuration.setStartNodeId((String)jsonParserFieldValue);
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