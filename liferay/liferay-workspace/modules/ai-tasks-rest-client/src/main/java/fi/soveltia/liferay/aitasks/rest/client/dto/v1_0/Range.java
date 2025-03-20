package fi.soveltia.liferay.aitasks.rest.client.dto.v1_0;

import fi.soveltia.liferay.aitasks.rest.client.function.UnsafeSupplier;
import fi.soveltia.liferay.aitasks.rest.client.serdes.v1_0.RangeSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Petteri Karttunen
 * @generated
 */
@Generated("")
public class Range implements Cloneable, Serializable {

	public static Range toDTO(String json) {
		return RangeSerDes.toDTO(json);
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

	public Object getGt() {
		return gt;
	}

	public void setGt(Object gt) {
		this.gt = gt;
	}

	public void setGt(UnsafeSupplier<Object, Exception> gtUnsafeSupplier) {
		try {
			gt = gtUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Object gt;

	public Object getGte() {
		return gte;
	}

	public void setGte(Object gte) {
		this.gte = gte;
	}

	public void setGte(UnsafeSupplier<Object, Exception> gteUnsafeSupplier) {
		try {
			gte = gteUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Object gte;

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

	public Object getLt() {
		return lt;
	}

	public void setLt(Object lt) {
		this.lt = lt;
	}

	public void setLt(UnsafeSupplier<Object, Exception> ltUnsafeSupplier) {
		try {
			lt = ltUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Object lt;

	public Object getLte() {
		return lte;
	}

	public void setLte(Object lte) {
		this.lte = lte;
	}

	public void setLte(UnsafeSupplier<Object, Exception> lteUnsafeSupplier) {
		try {
			lte = lteUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Object lte;

	@Override
	public Range clone() throws CloneNotSupportedException {
		return (Range)super.clone();
	}

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
		return RangeSerDes.toJSON(this);
	}

}