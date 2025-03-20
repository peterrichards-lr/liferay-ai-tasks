package fi.soveltia.liferay.aitasks.internal.task.chat.memory;

import dev.langchain4j.store.memory.chat.ChatMemoryStore;

/**
 * @author Petteri Karttunen
 */
public interface ChatMemoryStoreProvider {

	public ChatMemoryStore getChatMemoryStore();

}