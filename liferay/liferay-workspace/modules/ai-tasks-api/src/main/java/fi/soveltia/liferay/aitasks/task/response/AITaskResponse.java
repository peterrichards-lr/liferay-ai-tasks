package fi.soveltia.liferay.aitasks.task.response;

import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Petteri Karttunen
 */
@ProviderType
public interface AITaskResponse {

	public Map<String, Map<String, Object>> getDebugInfo();

	public Map<String, Object> getOutput();

	public String getTook();

}