package fi.soveltia.liferay.aitasks.rest.client.dto.v1_0;

import fi.soveltia.liferay.aitasks.rest.client.function.UnsafeSupplier;
import fi.soveltia.liferay.aitasks.rest.client.serdes.v1_0.ExistsSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Petteri Karttunen
 * @generated
 */
@Generated("")
public class Exists implements Cloneable, Serializable {

	public static Exists toDTO(String json) {
		return ExistsSerDes.toDTO(json);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setField(
		UnsafeSupplier<String, Exception> fieldUnsafeSupplier) {

		try {
			field = fieldUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String field;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<String, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String id;

	@Override
	public Exists clone() throws CloneNotSupportedException {
		return (Exists)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Exists)) {
			return false;
		}

		Exists exists = (Exists)object;

		return Objects.equals(toString(), exists.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ExistsSerDes.toJSON(this);
	}

}