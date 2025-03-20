package fi.soveltia.liferay.aitasks.internal.task.node.condition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Petteri Karttunen
 */
public class AITaskNodeConditionEvaluationResponse {

	public AITaskNodeConditionEvaluationResponse(
		Map<String, Object> debugInfo, boolean valid) {

		_debugInfo = debugInfo;
		_valid = valid;
	}

	public Map<String, Object> getDebugInfo() {
		if (_debugInfo == null) {
			_debugInfo = new HashMap<>();
		}

		return _debugInfo;
	}

	public boolean isValid() {
		return _valid;
	}

	private Map<String, Object> _debugInfo;
	private boolean _valid;

}