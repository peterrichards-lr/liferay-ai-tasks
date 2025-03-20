package fi.soveltia.liferay.aitasks.rest.client.dto.v1_0;

import fi.soveltia.liferay.aitasks.rest.client.function.UnsafeSupplier;
import fi.soveltia.liferay.aitasks.rest.client.serdes.v1_0.EdgeSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Petteri Karttunen
 * @generated
 */
@Generated("")
public class Edge implements Cloneable, Serializable {

	public static Edge toDTO(String json) {
		return EdgeSerDes.toDTO(json);
	}

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setSource(
		UnsafeSupplier<String, Exception> sourceUnsafeSupplier) {

		try {
			source = sourceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String source;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setTarget(
		UnsafeSupplier<String, Exception> targetUnsafeSupplier) {

		try {
			target = targetUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String target;

	public Object getUiConfiguration() {
		return uiConfiguration;
	}

	public void setUiConfiguration(Object uiConfiguration) {
		this.uiConfiguration = uiConfiguration;
	}

	public void setUiConfiguration(
		UnsafeSupplier<Object, Exception> uiConfigurationUnsafeSupplier) {

		try {
			uiConfiguration = uiConfigurationUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Object uiConfiguration;

	@Override
	public Edge clone() throws CloneNotSupportedException {
		return (Edge)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Edge)) {
			return false;
		}

		Edge edge = (Edge)object;

		return Objects.equals(toString(), edge.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return EdgeSerDes.toJSON(this);
	}

}