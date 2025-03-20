package fi.soveltia.liferay.aitasks.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class AITaskReadOnlyException extends PortalException {

	public AITaskReadOnlyException() {
	}

	public AITaskReadOnlyException(String msg) {
		super(msg);
	}

	public AITaskReadOnlyException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public AITaskReadOnlyException(Throwable throwable) {
		super(throwable);
	}

}