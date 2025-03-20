package fi.soveltia.liferay.aitasks.internal.web.cache;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import fi.soveltia.liferay.aitasks.configuration.AITasksConfiguration;

import java.util.function.BiFunction;

/**
 * @author Petteri Karttunen
 */
public class ChatModelAITaskNodeWebCacheItem implements WebCacheItem {

	public static Object get(
		AITasksConfiguration aiTasksConfiguration,
		BiFunction<String, String, Object> biFunction, String chatMemoryId,
		String id, String userMessage) {

		try {
			return WebCachePoolUtil.get(
				StringBundler.concat(
					ChatModelAITaskNodeWebCacheItem.class.getName(),
					StringPool.POUND, chatMemoryId, StringPool.POUND, id,
					StringPool.POUND, userMessage),
				new ChatModelAITaskNodeWebCacheItem(
					aiTasksConfiguration, biFunction, chatMemoryId,
					userMessage));
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return null;
		}
	}

	public ChatModelAITaskNodeWebCacheItem(
		AITasksConfiguration aiTasksConfiguration,
		BiFunction<String, String, Object> biFunction, String chatMemoryId,
		String userMessage) {

		_aiTasksConfiguration = aiTasksConfiguration;
		_biFunction = biFunction;
		_chatMemoryId = chatMemoryId;
		_userMessage = userMessage;
	}

	@Override
	public Object convert(String key) {
		try {
			Object result = _biFunction.apply(_chatMemoryId, _userMessage);

			if (result == null) {
				throw new RuntimeException("Result is null");
			}

			return result;
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public long getRefreshTime() {
		return _aiTasksConfiguration.taskNodeCacheTimeout();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ChatModelAITaskNodeWebCacheItem.class);

	private final AITasksConfiguration _aiTasksConfiguration;
	private final BiFunction<String, String, Object> _biFunction;
	private final String _chatMemoryId;
	private final String _userMessage;

}