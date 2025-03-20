package fi.soveltia.liferay.aitasks.task.response;

import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Petteri Karttunen
 */
@ProviderType
public interface AITaskResponseBuilder {

	public AITaskResponseBuilder addDebugInfo(
		Map<String, Object> debugInfo, String id);

	public AITaskResponseBuilder addOutput(String key, Object value);

	public AITaskResponse build();

	public AITaskResponseBuilder took(String took);

}