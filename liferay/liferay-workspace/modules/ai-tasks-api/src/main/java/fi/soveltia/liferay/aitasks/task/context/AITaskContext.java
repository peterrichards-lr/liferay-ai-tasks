package fi.soveltia.liferay.aitasks.task.context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Petteri Karttunen
 */
public class AITaskContext {

	public AITaskContext(
		String aiTaskExternalReferenceCode, long companyId, Locale locale,
		long userId) {

		_aiTaskExternalReferenceCode = aiTaskExternalReferenceCode;
		_companyId = companyId;
		_locale = locale;
		_userId = userId;
	}

	public void addAITaskContextParameter(
		AITaskContextParameter aiTaskContextParameter, String key) {

		if (_aiTaskContextParameters == null) {
			_aiTaskContextParameters = new HashMap<>();
		}

		_aiTaskContextParameters.putIfAbsent(key, aiTaskContextParameter);
	}

	public AITaskContextParameter getAITaskContextParameter(String name) {
		if (_aiTaskContextParameters == null) {
			return null;
		}

		return _aiTaskContextParameters.get(name);
	}

	public Map<String, AITaskContextParameter> getAITaskContextParameters() {
		if (_aiTaskContextParameters == null) {
			return Collections.emptyMap();
		}

		return _aiTaskContextParameters;
	}

	public String getAITaskExternalReferenceCode() {
		return _aiTaskExternalReferenceCode;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public Locale getLocale() {
		return _locale;
	}

	public long getUserId() {
		return _userId;
	}

	private Map<String, AITaskContextParameter> _aiTaskContextParameters;
	private final String _aiTaskExternalReferenceCode;
	private final long _companyId;
	private final Locale _locale;
	private final long _userId;

}