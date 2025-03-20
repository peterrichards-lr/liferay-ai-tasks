package fi.soveltia.liferay.aitasks.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Petteri Karttunen
 */
public class AITaskTestException extends PortalException {

	public AITaskTestException() {
	}

	public AITaskTestException(String msg) {
		super(msg);
	}

	public AITaskTestException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public AITaskTestException(Throwable throwable) {
		super(throwable);
	}

}