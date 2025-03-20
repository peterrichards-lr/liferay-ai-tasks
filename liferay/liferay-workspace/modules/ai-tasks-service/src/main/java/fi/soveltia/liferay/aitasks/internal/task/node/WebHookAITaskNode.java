
package fi.soveltia.liferay.aitasks.internal.task.node;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import fi.soveltia.liferay.aitasks.spi.task.node.AITaskNode;
import fi.soveltia.liferay.aitasks.task.context.AITaskContext;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeInformation;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeResponse;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Calls any remote
 *
 * @author Louis-Guillaume Durand
 * @author Petteri Karttune
 */
@Component(property = "ai.task.node.type=webhook", service = AITaskNode.class)
public class WebHookAITaskNode extends BaseAITaskNode implements AITaskNode {

	@Override
	public AITaskNodeResponse execute(
		AITaskContext aiTaskContext, boolean debug, String id,
		Map<String, Object> input, JSONObject jsonObject) {

		Http.Options options = new Http.Options();

		options.setLocation(GetterUtil.getString(jsonObject.get("url")));
		options.setMethod(_getMethod(jsonObject));

		_setBody(jsonObject, options);
		_setHeaders(jsonObject, options);

		try {
			String response = _http.URLtoString(options);

			return toAITaskNodeResponse(
				aiTaskContext, debug,
				_getDebugInfo(debug, options.getResponse(), response),
				jsonObject, _http.URLtoString(options));
		}
		catch (Exception exception) {
			toExceptionAITaskNodeResponse(exception);
		}

		return null;
	}

	@Override
	public AITaskNodeInformation getAITaskNodeInformation() {
		return new AITaskNodeInformation("webhook", "input");
	}

	private Map<String, Object> _getDebugInfo(
		boolean debug, Http.Response response, String responseString) {

		if (!debug) {
			return null;
		}

		return HashMapBuilder.<String, Object>put(
			"contentLength", response.getContentLength()
		).put(
			"contentType", response.getContentType()
		).put(
			"responseCode", response.getResponseCode()
		).put(
			"responseString", responseString
		).build();
	}

	private Http.Method _getMethod(JSONObject jsonObject) {
		String method = jsonObject.getString("method");

		if (Validator.isBlank("method")) {
			return Http.Method.GET;
		}

		if (StringUtil.equalsIgnoreCase("post", method)) {
			return Http.Method.DELETE;
		}
		else if (StringUtil.equalsIgnoreCase("post", method)) {
			return Http.Method.HEAD;
		}
		else if (StringUtil.equalsIgnoreCase("post", method)) {
			return Http.Method.POST;
		}
		else if (StringUtil.equalsIgnoreCase("patch", method)) {
			return Http.Method.PATCH;
		}
		else if (StringUtil.equalsIgnoreCase("put", method)) {
			return Http.Method.PUT;
		}

		return Http.Method.GET;
	}

	private void _setBody(JSONObject jsonObject, Http.Options options) {
		JSONObject bodyJSONObject = jsonObject.getJSONObject("body");

		if (bodyJSONObject == null) {
			return;
		}

		options.setBody(
			bodyJSONObject.toString(), ContentTypes.APPLICATION_JSON,
			StringPool.UTF8);
	}

	private void _setHeaders(JSONObject jsonObject, Http.Options options) {
		JSONObject headersJSONObject = jsonObject.getJSONObject("headers");

		if (headersJSONObject == null) {
			return;
		}

		options.setHeaders(_toStringValues(headersJSONObject.toMap()));
	}

	private Map<String, String> _toStringValues(Map<String, Object> map) {
		Map<String, String> stringValueMap = new HashMap<>();

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			stringValueMap.put(
				entry.getKey(), String.valueOf(entry.getValue()));
		}

		return stringValueMap;
	}

	@Reference
	private Http _http;

}