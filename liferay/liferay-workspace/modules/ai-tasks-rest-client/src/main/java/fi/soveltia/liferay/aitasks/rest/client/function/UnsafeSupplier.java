package fi.soveltia.liferay.aitasks.rest.client.function;

import javax.annotation.Generated;

/**
 * @author Petteri Karttunen
 * @generated
 */
@FunctionalInterface
@Generated("")
public interface UnsafeSupplier<T, E extends Throwable> {

	public T get() throws E;

}