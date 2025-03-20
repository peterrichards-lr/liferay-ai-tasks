package fi.soveltia.liferay.aitasks.internal.task.chat.model.listener;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import dev.langchain4j.model.chat.listener.ChatModelListener;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Petteri Karttunen
 */
@Component(service = ChatModelListenerProvider.class)
public class ChatModelListenerProviderImpl
	implements ChatModelListenerProvider {

	@Override
	public ChatModelListener getChatModelListener(String name) {
		if (Validator.isBlank(name)) {
			return null;
		}

		ChatModelListener chatModelListener = _serviceTrackerMap.getService(
			name);

		if (chatModelListener != null) {
			return chatModelListener;
		}

		_log.error("Chat model listener " + name + " could not be resolved");

		return null;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, ChatModelListener.class, "chat.model.listener.name");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ChatModelListenerProviderImpl.class);

	private ServiceTrackerMap<String, ChatModelListener> _serviceTrackerMap;

}