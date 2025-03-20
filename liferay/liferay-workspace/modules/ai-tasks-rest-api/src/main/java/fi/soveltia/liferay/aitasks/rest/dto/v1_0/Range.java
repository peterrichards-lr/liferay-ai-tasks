package fi.soveltia.liferay.aitasks.rest.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Petteri Karttunen
 * @generated
 */
@Generated("")
@GraphQLName("Range")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Range")
public class Range implements Serializable {

	public static Range toDTO(String json) {
		return ObjectMapperUtil.readValue(Range.class, json);
	}

	public static Range unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Range.class, json);
	}

	@Schema
	public String getField() {
		if (_fieldSupplier != null) {
			field = _fieldSupplier.get();

			_fieldSupplier = null;
		}

		return field;
	}

	public void setField(String field) {
		this.field = field;

		_fieldSupplier = null;
	}

	@JsonIgnore
	public void setField(
		UnsafeSupplier<String, Exception> fieldUnsafeSupplier) {

		_fieldSupplier = () -> {
			try {
				return fieldUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String field;

	@JsonIgnore
	private Supplier<String> _fieldSupplier;

	@Schema
	@Valid
	public Object getGt() {
		if (_gtSupplier != null) {
			gt = _gtSupplier.get();

			_gtSupplier = null;
		}

		return gt;
	}

	public void setGt(Object gt) {
		this.gt = gt;

		_gtSupplier = null;
	}

	@JsonIgnore
	public void setGt(UnsafeSupplier<Object, Exception> gtUnsafeSupplier) {
		_gtSupplier = () -> {
			try {
				return gtUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Object gt;

	@JsonIgnore
	private Supplier<Object> _gtSupplier;

	@Schema
	@Valid
	public Object getGte() {
		if (_gteSupplier != null) {
			gte = _gteSupplier.get();

			_gteSupplier = null;
		}

		return gte;
	}

	public void setGte(Object gte) {
		this.gte = gte;

		_gteSupplier = null;
	}

	@JsonIgnore
	public void setGte(UnsafeSupplier<Object, Exception> gteUnsafeSupplier) {
		_gteSupplier = () -> {
			try {
				return gteUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Object gte;

	@JsonIgnore
	private Supplier<Object> _gteSupplier;

	@Schema
	public String getId() {
		if (_idSupplier != null) {
			id = _idSupplier.get();

			_idSupplier = null;
		}

		return id;
	}

	public void setId(String id) {
		this.id = id;

		_idSupplier = null;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<String, Exception> idUnsafeSupplier) {
		_idSupplier = () -> {
			try {
				return idUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String id;

	@JsonIgnore
	private Supplier<String> _idSupplier;

	@Schema
	@Valid
	public Object getLt() {
		if (_ltSupplier != null) {
			lt = _ltSupplier.get();

			_ltSupplier = null;
		}

		return lt;
	}

	public void setLt(Object lt) {
		this.lt = lt;

		_ltSupplier = null;
	}

	@JsonIgnore
	public void setLt(UnsafeSupplier<Object, Exception> ltUnsafeSupplier) {
		_ltSupplier = () -> {
			try {
				return ltUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Object lt;

	@JsonIgnore
	private Supplier<Object> _ltSupplier;

	@Schema
	@Valid
	public Object getLte() {
		if (_lteSupplier != null) {
			lte = _lteSupplier.get();

			_lteSupplier = null;
		}

		return lte;
	}

	public void setLte(Object lte) {
		this.lte = lte;

		_lteSupplier = null;
	}

	@JsonIgnore
	public void setLte(UnsafeSupplier<Object, Exception> lteUnsafeSupplier) {
		_lteSupplier = () -> {
			try {
				return lteUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Object lte;

	@JsonIgnore
	private Supplier<Object> _lteSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Range)) {
			return false;
		}

		Range range = (Range)object;

		return Objects.equals(toString(), range.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		String field = getField();

		if (field != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"field\": ");

			sb.append("\"");

			sb.append(_escape(field));

			sb.append("\"");
		}

		Object gt = getGt();

		if (gt != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"gt\": ");

			if (gt instanceof Map) {
				sb.append(JSONFactoryUtil.createJSONObject((Map<?, ?>)gt));
			}
			else if (gt instanceof String) {
				sb.append("\"");
				sb.append(_escape((String)gt));
				sb.append("\"");
			}
			else {
				sb.append(gt);
			}
		}

		Object gte = getGte();

		if (gte != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"gte\": ");

			if (gte instanceof Map) {
				sb.append(JSONFactoryUtil.createJSONObject((Map<?, ?>)gte));
			}
			else if (gte instanceof String) {
				sb.append("\"");
				sb.append(_escape((String)gte));
				sb.append("\"");
			}
			else {
				sb.append(gte);
			}
		}

		String id = getId();

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append("\"");

			sb.append(_escape(id));

			sb.append("\"");
		}

		Object lt = getLt();

		if (lt != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lt\": ");

			if (lt instanceof Map) {
				sb.append(JSONFactoryUtil.createJSONObject((Map<?, ?>)lt));
			}
			else if (lt instanceof String) {
				sb.append("\"");
				sb.append(_escape((String)lt));
				sb.append("\"");
			}
			else {
				sb.append(lt);
			}
		}

		Object lte = getLte();

		if (lte != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lte\": ");

			if (lte instanceof Map) {
				sb.append(JSONFactoryUtil.createJSONObject((Map<?, ?>)lte));
			}
			else if (lte instanceof String) {
				sb.append("\"");
				sb.append(_escape((String)lte));
				sb.append("\"");
			}
			else {
				sb.append(lte);
			}
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "fi.soveltia.liferay.aitasks.rest.dto.v1_0.Range",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
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
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof Map) {
						sb.append(_toJSON((Map<String, ?>)valueArray[i]));
					}
					else if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

	private Map<String, Serializable> _extendedProperties;

}