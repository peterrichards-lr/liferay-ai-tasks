package fi.soveltia.liferay.aitasks.internal.task.tool;

import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Petteri Karttunen
 */
public interface AIToolsProvider {

	public Object getTool(JSONObject configurationJSONObject, String key);

}