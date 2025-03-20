
package fi.soveltia.liferay.aitasks.internal.task.chat.memory;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;

import fi.soveltia.liferay.aitasks.model.AITask;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.Configuration;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.Node;
import fi.soveltia.liferay.aitasks.service.AITaskService;
import fi.soveltia.liferay.aitasks.task.chat.memory.ChatMemoryManager;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(service = ChatMemoryManager.class)
public class ChatMemoryManagerImpl implements ChatMemoryManager {

	public void clearChatMemory(
		long companyId, String externalReferenceCode, String nodeId,
		long userId) {

		try {
			AITask aiTask = _aiTaskService.fetchAITaskByExternalReferenceCode(
				externalReferenceCode, companyId);

			if (aiTask == null) {
				_log.error(
					StringBundler.concat(
						"AITask ", externalReferenceCode, " not found"));

				return;
			}

			Node node = _getNode(aiTask, nodeId);

			if (node == null) {
				_log.error(
					StringBundler.concat("AITask node ", nodeId, " not found"));

				return;
			}

			if (StringUtil.endsWith(node.getType(), "ChatModel")) {
				_clearChatMemory(
					companyId, externalReferenceCode, nodeId, userId);
			}
		}
		catch (Exception exception) {
			_log.error(exception);
		}
	}

	private void _clearChatMemory(
		long companyId, String externalReferenceCode, String nodeId,
		long userId) {

		MessageWindowChatMemory memory = MessageWindowChatMemory.builder(
		).id(
			StringBundler.concat(
				companyId, StringPool.POUND, userId, StringPool.POUND,
				externalReferenceCode, StringPool.POUND, nodeId)
		).chatMemoryStore(
			_chatMemoryStoreProvider.getChatMemoryStore()
		).maxMessages(
			1
		).build();

		memory.clear();
	}

	private Node _getNode(AITask aiTask, String nodeId) {
		Configuration configuration = Configuration.toDTO(
			aiTask.getConfigurationJSON());

		for (Node node : configuration.getNodes()) {
			if (StringUtil.equals(node.getId(), nodeId)) {
				return node;
			}
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ChatMemoryManagerImpl.class);

	@Reference
	private AITaskService _aiTaskService;

	@Reference
	private ChatMemoryStoreProvider _chatMemoryStoreProvider;

}