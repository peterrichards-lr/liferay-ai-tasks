
package fi.soveltia.liferay.aitasks.internal.task.node;

import fi.soveltia.liferay.aitasks.task.node.AITaskNodeResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Petteri Karttunen
 */
public class AITaskNodeResponseImpl implements AITaskNodeResponse {

	public AITaskNodeResponseImpl(
		Map<String, Object> debugInfo, Map<String, Object> output) {

		_debugInfo = debugInfo;
		_output = output;
	}

	@Override
	public Map<String, Object> getDebugInfo() {
		if (_debugInfo == null) {
			_debugInfo = new HashMap<>();
		}

		return _debugInfo;
	}

	@Override
	public Map<String, Object> getOutput() {
		if (_output == null) {
			_output = new HashMap<>();
		}

		return _output;
	}

	private Map<String, Object> _debugInfo;
	private Map<String, Object> _output;

}