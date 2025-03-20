package fi.soveltia.liferay.aitasks.rest.client.dto.v1_0;

import fi.soveltia.liferay.aitasks.rest.client.function.UnsafeSupplier;
import fi.soveltia.liferay.aitasks.rest.client.serdes.v1_0.AITaskRequestSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Petteri Karttunen
 * @generated
 */
@Generated("")
public class AITaskRequest implements Cloneable, Serializable {

	public static AITaskRequest toDTO(String json) {
		return AITaskRequestSerDes.toDTO(json);
	}

	public Object getInput() {
		return input;
	}

	public void setInput(Object input) {
		this.input = input;
	}

	public void setInput(
		UnsafeSupplier<Object, Exception> inputUnsafeSupplier) {

		try {
			input = inputUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Object input;

	@Override
	public AITaskRequest clone() throws CloneNotSupportedException {
		return (AITaskRequest)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AITaskRequest)) {
			return false;
		}

		AITaskRequest aiTaskRequest = (AITaskRequest)object;

		return Objects.equals(toString(), aiTaskRequest.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return AITaskRequestSerDes.toJSON(this);
	}

}