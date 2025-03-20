package fi.soveltia.liferay.aitasks.internal.task.tool;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

import fi.soveltia.liferay.aitasks.spi.task.tool.AITaskTool;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Louis-Guillaume Durand
 * @author Petteri Karttunen
 */
@Component(property = "ai.task.tool.name=webhook", service = AITaskTool.class)
public class WebHookAITaskTool implements AITaskTool {

	@Override
	public Object getExecutor(JSONObject jsonObject) {
		return new Executor(jsonObject);
	}

	public class Executor {

		public Executor(JSONObject configurationJSONObject) {
			_jsonObject = configurationJSONObject;
		}

		@Tool(
			"Calls an external webhook. Use this only when only the URL is given."
		)
		public String callURL(@P("The URL to call") String url)
			throws IOException, JSONException {

			Http.Options options = new Http.Options();

			url = StringUtil.trim(url);

			options.setLocation(url);

			options.setMethod(_getMethod());

			_setBody(null, options);
			_setHeaders(options);

			return http.URLtoString(options);
		}

		@Tool(
			"Sends the given JSON data to the external webhook. Use this only when the URL is not given"
		)
		public String sendJSON(@P("The JSON data to send") String json)
			throws IOException, JSONException {

			Http.Options options = new Http.Options();

			options.setLocation(_getLocation(null));
			options.setMethod(_getMethod());

			_setBody(json, options);
			_setHeaders(options);

			return http.URLtoString(options);
		}

		@Tool(
			"Send JSON data to the external webhook tool. Use this only when both the JSON and the URL are given."
		)
		public String sendJSON(
				@P("The JSON data to send") String json,
				@P("The URL to use to send the data") String url)
			throws IOException, JSONException {

			Http.Options options = new Http.Options();

			options.setLocation(_getLocation(url));
			options.setMethod(_getMethod());

			_setBody(json, options);
			_setHeaders(options);

			return http.URLtoString(options);
		}

		private String _getLocation(String url) {
			if (!Validator.isBlank(url)) {
				return url;
			}

			return _jsonObject.getString("url");
		}

		private String _getMergedJSONString(
			JSONObject bodyJSONObject, JSONObject parameterJSONObject) {

			for (String key : parameterJSONObject.keySet()) {
				bodyJSONObject.put(key, bodyJSONObject.get(key));
			}

			return bodyJSONObject.toString();
		}

		private Http.Method _getMethod() {
			String method = _jsonObject.getString("method");

			if (Validator.isBlank("method")) {
				return Http.Method.POST;
			}

			if (StringUtil.equalsIgnoreCase("post", method)) {
				return Http.Method.DELETE;
			}
			else if (StringUtil.equalsIgnoreCase("get", method)) {
				return Http.Method.GET;
			}
			else if (StringUtil.equalsIgnoreCase("post", method)) {
				return Http.Method.HEAD;
			}
			else if (StringUtil.equalsIgnoreCase("patch", method)) {
				return Http.Method.PATCH;
			}
			else if (StringUtil.equalsIgnoreCase("put", method)) {
				return Http.Method.PUT;
			}

			return Http.Method.POST;
		}

		private void _setBody(String json, Http.Options options)
			throws JSONException {

			JSONObject bodyJSONObject = _jsonObject.getJSONObject("body");

			JSONObject parameterJSONObject = null;

			if (!Validator.isBlank(json)) {
				parameterJSONObject = jsonFactory.createJSONObject(json);
			}

			if ((bodyJSONObject == null) && (parameterJSONObject == null)) {
				return;
			}

			if (bodyJSONObject == null) {
				options.setBody(
					parameterJSONObject.toString(),
					ContentTypes.APPLICATION_JSON, StringPool.UTF8);

				return;
			}

			if (parameterJSONObject == null) {
				options.setBody(
					bodyJSONObject.toString(), ContentTypes.APPLICATION_JSON,
					StringPool.UTF8);

				return;
			}

			options.setBody(
				_getMergedJSONString(bodyJSONObject, parameterJSONObject),
				ContentTypes.APPLICATION_JSON, StringPool.UTF8);
		}

		private void _setHeaders(Http.Options options) {
			JSONObject headersJSONObject = _jsonObject.getJSONObject("headers");

			if (headersJSONObject == null) {
				options.setHeaders(
					HashMapBuilder.put(
						"Content-Type", "application/json"
					).build());

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

		private final JSONObject _jsonObject;

	}

	@Reference
	protected Http http;

	@Reference
	protected JSONFactory jsonFactory;

}