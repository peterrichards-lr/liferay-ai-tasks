package fi.soveltia.liferay.aitasks.task.chat.memory;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Petteri Karttunen
 */
@ProviderType
public interface ChatMemoryManager {

	public void clearChatMemory(
		long companyId, String externalReferenceCode, String nodeId,
		long userId);

}