package fi.soveltia.liferay.aitasks.internal.task.tool;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import fi.soveltia.liferay.aitasks.spi.task.tool.AITaskTool;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Petteri Karttunen
 */
@Component(service = AIToolsProvider.class)
public class AIToolsProviderImpl implements AIToolsProvider {

	@Override
	public Object getTool(JSONObject configurationJSONObject, String name) {
		if (Validator.isBlank(name)) {
			return null;
		}

		AITaskTool aiTaskTool = _serviceTrackerMap.getService(name);

		if (aiTaskTool != null) {
			return aiTaskTool.getExecutor(configurationJSONObject);
		}

		_log.error("AI task tool " + name + " could not be resolved");

		return null;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, AITaskTool.class, "ai.task.tool.name");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AIToolsProviderImpl.class);

	private ServiceTrackerMap<String, AITaskTool> _serviceTrackerMap;

}