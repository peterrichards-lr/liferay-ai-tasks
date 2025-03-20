package fi.soveltia.liferay.aitasks.internal.task.ai.services;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.UserMessage;

/**
 * @author Petteri Karttunen
 */
public interface AIChatAssistant {

	public Result<String> chat(
		@MemoryId String memoryId, @UserMessage String userMessage);

}