package fi.soveltia.liferay.aitasks.task.node;

import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Petteri Karttunen
 */
@ProviderType
public interface AITaskNodeResponse {

	public Map<String, Object> getDebugInfo();

	public Map<String, Object> getOutput();

}