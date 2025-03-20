package fi.soveltia.liferay.aitasks.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class AITaskTitleException extends PortalException {

	public AITaskTitleException() {
	}

	public AITaskTitleException(String msg) {
		super(msg);
	}

	public AITaskTitleException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public AITaskTitleException(Throwable throwable) {
		super(throwable);
	}

}