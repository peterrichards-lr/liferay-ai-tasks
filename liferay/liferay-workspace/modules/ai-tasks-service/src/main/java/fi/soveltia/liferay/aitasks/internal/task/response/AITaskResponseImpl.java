

package fi.soveltia.liferay.aitasks.internal.task.response;

import fi.soveltia.liferay.aitasks.task.response.AITaskResponse;

import java.util.Map;

/**
 * @author Petteri Karttunen
 */
public class AITaskResponseImpl implements AITaskResponse {

	public AITaskResponseImpl(
		Map<String, Map<String, Object>> debugInfo, Map<String, Object> output,
		String took) {

		_debugInfo = debugInfo;
		_output = output;
		_took = took;
	}

	@Override
	public Map<String, Map<String, Object>> getDebugInfo() {
		return _debugInfo;
	}

	@Override
	public Map<String, Object> getOutput() {
		return _output;
	}

	@Override
	public String getTook() {
		return _took;
	}

	private final Map<String, Map<String, Object>> _debugInfo;
	private final Map<String, Object> _output;
	private final String _took;

}