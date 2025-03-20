package fi.soveltia.liferay.aitasks.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Petteri Karttunen
 */
public class AITaskConfigurationJSONException extends PortalException {

	public AITaskConfigurationJSONException() {
	}

	public AITaskConfigurationJSONException(String msg) {
		super(msg);
	}

	public AITaskConfigurationJSONException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public AITaskConfigurationJSONException(Throwable throwable) {
		super(throwable);
	}

}