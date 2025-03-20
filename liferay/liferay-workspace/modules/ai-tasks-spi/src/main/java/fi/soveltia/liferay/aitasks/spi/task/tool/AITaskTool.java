package fi.soveltia.liferay.aitasks.spi.task.tool;

import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Petteri Karttunen
 */
public interface AITaskTool {

	public Object getExecutor(JSONObject configurationJSONObject);

}