

package fi.soveltia.liferay.aitasks.internal.task.response;

import fi.soveltia.liferay.aitasks.task.response.AITaskResponse;
import fi.soveltia.liferay.aitasks.task.response.AITaskResponseBuilder;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(service = AITaskResponseBuilder.class)
public class AITaskResponseBuilderImpl implements AITaskResponseBuilder {

	@Override
	public AITaskResponseBuilder addDebugInfo(
		Map<String, Object> debugInfo, String id) {

		if (_debugInfo == null) {
			_debugInfo = new HashMap<>();
		}

		if (_debugInfo.containsKey(id)) {
			Map<String, Object> map = _debugInfo.get(id);

			if (map == null) {
				map = new HashMap<>();
			}

			map.putAll(debugInfo);
		}
		else {
			_debugInfo.put(id, debugInfo);
		}

		return this;
	}

	@Override
	public AITaskResponseBuilder addOutput(String key, Object value) {
		if (_output == null) {
			_output = new HashMap<>();
		}

		_output.put(key, value);

		return this;
	}

	@Override
	public AITaskResponse build() {
		return new AITaskResponseImpl(_debugInfo, _output, _took);
	}

	@Override
	public AITaskResponseBuilder took(String took) {
		_took = took;

		return this;
	}

	private Map<String, Map<String, Object>> _debugInfo;
	private Map<String, Object> _output;
	private String _took;

}