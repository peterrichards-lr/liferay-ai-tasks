package fi.soveltia.liferay.aitasks.rest.client.dto.v1_0;

import fi.soveltia.liferay.aitasks.rest.client.function.UnsafeSupplier;
import fi.soveltia.liferay.aitasks.rest.client.serdes.v1_0.AITaskResponseSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Petteri Karttunen
 * @generated
 */
@Generated("")
public class AITaskResponse implements Cloneable, Serializable {

	public static AITaskResponse toDTO(String json) {
		return AITaskResponseSerDes.toDTO(json);
	}

	public Object getDebugInfo() {
		return debugInfo;
	}

	public void setDebugInfo(Object debugInfo) {
		this.debugInfo = debugInfo;
	}

	public void setDebugInfo(
		UnsafeSupplier<Object, Exception> debugInfoUnsafeSupplier) {

		try {
			debugInfo = debugInfoUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Object debugInfo;

	public Object getOutput() {
		return output;
	}

	public void setOutput(Object output) {
		this.output = output;
	}

	public void setOutput(
		UnsafeSupplier<Object, Exception> outputUnsafeSupplier) {

		try {
			output = outputUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Object output;

	public String getTook() {
		return took;
	}

	public void setTook(String took) {
		this.took = took;
	}

	public void setTook(UnsafeSupplier<String, Exception> tookUnsafeSupplier) {
		try {
			took = tookUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String took;

	@Override
	public AITaskResponse clone() throws CloneNotSupportedException {
		return (AITaskResponse)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AITaskResponse)) {
			return false;
		}

		AITaskResponse aiTaskResponse = (AITaskResponse)object;

		return Objects.equals(toString(), aiTaskResponse.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return AITaskResponseSerDes.toJSON(this);
	}

}