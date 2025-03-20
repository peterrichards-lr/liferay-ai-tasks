package fi.soveltia.liferay.aitasks.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchAITaskException extends NoSuchModelException {

	public NoSuchAITaskException() {
	}

	public NoSuchAITaskException(String msg) {
		super(msg);
	}

	public NoSuchAITaskException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchAITaskException(Throwable throwable) {
		super(throwable);
	}

}