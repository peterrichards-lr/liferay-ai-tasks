package fi.soveltia.liferay.aitasks.rest.client.serdes.v1_0;

import fi.soveltia.liferay.aitasks.rest.client.dto.v1_0.Range;
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
public class RangeSerDes {

	public static Range toDTO(String json) {
		RangeJSONParser rangeJSONParser = new RangeJSONParser();

		return rangeJSONParser.parseToDTO(json);
	}

	public static Range[] toDTOs(String json) {
		RangeJSONParser rangeJSONParser = new RangeJSONParser();

		return rangeJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Range range) {
		if (range == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (range.getField() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"field\": ");

			sb.append("\"");

			sb.append(_escape(range.getField()));

			sb.append("\"");
		}

		if (range.getGt() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"gt\": ");

			if (range.getGt() instanceof String) {
				sb.append("\"");
				sb.append((String)range.getGt());
				sb.append("\"");
			}
			else {
				sb.append(range.getGt());
			}
		}

		if (range.getGte() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"gte\": ");

			if (range.getGte() instanceof String) {
				sb.append("\"");
				sb.append((String)range.getGte());
				sb.append("\"");
			}
			else {
				sb.append(range.getGte());
			}
		}

		if (range.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append("\"");

			sb.append(_escape(range.getId()));

			sb.append("\"");
		}

		if (range.getLt() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lt\": ");

			if (range.getLt() instanceof String) {
				sb.append("\"");
				sb.append((String)range.getLt());
				sb.append("\"");
			}
			else {
				sb.append(range.getLt());
			}
		}

		if (range.getLte() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lte\": ");

			if (range.getLte() instanceof String) {
				sb.append("\"");
				sb.append((String)range.getLte());
				sb.append("\"");
			}
			else {
				sb.append(range.getLte());
			}
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		RangeJSONParser rangeJSONParser = new RangeJSONParser();

		return rangeJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Range range) {
		if (range == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (range.getField() == null) {
			map.put("field", null);
		}
		else {
			map.put("field", String.valueOf(range.getField()));
		}

		if (range.getGt() == null) {
			map.put("gt", null);
		}
		else {
			map.put("gt", String.valueOf(range.getGt()));
		}

		if (range.getGte() == null) {
			map.put("gte", null);
		}
		else {
			map.put("gte", String.valueOf(range.getGte()));
		}

		if (range.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(range.getId()));
		}

		if (range.getLt() == null) {
			map.put("lt", null);
		}
		else {
			map.put("lt", String.valueOf(range.getLt()));
		}

		if (range.getLte() == null) {
			map.put("lte", null);
		}
		else {
			map.put("lte", String.valueOf(range.getLte()));
		}

		return map;
	}

	public static class RangeJSONParser extends BaseJSONParser<Range> {

		@Override
		protected Range createDTO() {
			return new Range();
		}

		@Override
		protected Range[] createDTOArray(int size) {
			return new Range[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "field")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "gt")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "gte")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "lt")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "lte")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			Range range, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "field")) {
				if (jsonParserFieldValue != null) {
					range.setField((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "gt")) {
				if (jsonParserFieldValue != null) {
					range.setGt((Object)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "gte")) {
				if (jsonParserFieldValue != null) {
					range.setGte((Object)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					range.setId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "lt")) {
				if (jsonParserFieldValue != null) {
					range.setLt((Object)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "lte")) {
				if (jsonParserFieldValue != null) {
					range.setLte((Object)jsonParserFieldValue);
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