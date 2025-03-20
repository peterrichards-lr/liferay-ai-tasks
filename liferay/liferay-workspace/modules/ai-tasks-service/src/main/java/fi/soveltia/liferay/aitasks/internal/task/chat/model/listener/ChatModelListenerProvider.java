package fi.soveltia.liferay.aitasks.internal.task.chat.model.listener;

import dev.langchain4j.model.chat.listener.ChatModelListener;

/**
 * @author Petteri Karttunen
 */
public interface ChatModelListenerProvider {

	public ChatModelListener getChatModelListener(String name);

}