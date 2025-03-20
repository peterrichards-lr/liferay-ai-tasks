package fi.soveltia.liferay.aitasks.exception;

import com.liferay.portal.kernel.exception.DuplicateExternalReferenceCodeException;

/**
 * @author Brian Wing Shun Chan
 */
public class DuplicateAITaskExternalReferenceCodeException
	extends DuplicateExternalReferenceCodeException {

	public DuplicateAITaskExternalReferenceCodeException() {
	}

	public DuplicateAITaskExternalReferenceCodeException(String msg) {
		super(msg);
	}

	public DuplicateAITaskExternalReferenceCodeException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public DuplicateAITaskExternalReferenceCodeException(Throwable throwable) {
		super(throwable);
	}

}