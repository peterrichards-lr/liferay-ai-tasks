package fi.soveltia.liferay.aitasks.internal.task.node;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.WebSearchContentRetriever;
import dev.langchain4j.rag.query.router.DefaultQueryRouter;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.Result;
import dev.langchain4j.web.search.google.customsearch.GoogleCustomWebSearchEngine;

import fi.soveltia.liferay.aitasks.configuration.AITasksConfigurationProvider;
import fi.soveltia.liferay.aitasks.internal.task.ai.services.AIChatAssistant;
import fi.soveltia.liferay.aitasks.internal.task.chat.memory.ChatMemoryStoreProvider;
import fi.soveltia.liferay.aitasks.internal.task.chat.model.listener.ChatModelListenerProvider;
import fi.soveltia.liferay.aitasks.internal.task.tool.AIToolsProvider;
import fi.soveltia.liferay.aitasks.internal.util.PromptUtil;
import fi.soveltia.liferay.aitasks.internal.util.SetterUtil;
import fi.soveltia.liferay.aitasks.internal.web.cache.ChatModelAITaskNodeWebCacheItem;
import fi.soveltia.liferay.aitasks.spi.task.node.AITaskNode;
import fi.soveltia.liferay.aitasks.task.context.AITaskContext;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeResponse;

import java.time.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
public abstract class BaseChatModelAITaskNode
	extends BaseAITaskNode implements AITaskNode {

	@Override
	public AITaskNodeResponse execute(
		AITaskContext aiTaskContext, boolean debug, String id,
		Map<String, Object> input, JSONObject jsonObject) {

		String systemMessage = PromptUtil.getSystemMessage(
			aiTaskContext, input, jsonObject);

		String userMessage = PromptUtil.getUserMessage(
			aiTaskContext, input, jsonObject);

		if (userMessage == null) {
			return null;
		}

		AIChatAssistant aiChatAssistant = getAIChatAssistant(
			jsonObject, systemMessage);

		if (jsonObject.getBoolean("useCache")) {
			String json = jsonObject.toString();

			Result<String> result =
				(Result<String>)ChatModelAITaskNodeWebCacheItem.get(
					aiTasksConfigurationProvider.getCompanyConfiguration(
						aiTaskContext.getCompanyId()),
					aiChatAssistant::chat, getMemoryId(aiTaskContext, id),
					String.valueOf(json.hashCode()), userMessage);

			return toAITaskNodeResponse(
				aiTaskContext, debug,
				_getDebugInfo(debug, result, systemMessage, userMessage),
				jsonObject, result);
		}

		Result<?> result = aiChatAssistant.chat(
			getMemoryId(aiTaskContext, id), userMessage);

		return toAITaskNodeResponse(
			aiTaskContext, debug,
			_getDebugInfo(debug, result, systemMessage, userMessage),
			jsonObject, result);
	}

	protected AIChatAssistant getAIChatAssistant(
		JSONObject jsonObject, String systemMessage) {

		AiServices<AIChatAssistant> builder = AiServices.builder(
			AIChatAssistant.class
		).chatLanguageModel(
			getChatLanguageModel(jsonObject)
		);

		setChatMemoryProvider(builder, jsonObject);

		if (jsonObject.has("webSearchRetrievalAugmentor")) {
			RetrievalAugmentor retrievalAugmentor =
				_getWebSearchRetrievalAugmentor(
					jsonObject.getJSONObject("webSearchRetrievalAugmentor"));

			if (retrievalAugmentor != null) {
				builder.retrievalAugmentor(retrievalAugmentor);
			}
		}

		if (!Validator.isBlank(systemMessage)) {
			builder.systemMessageProvider(memoryId -> systemMessage);
		}

		ListUtil.isNotEmptyForEach(
			getTools(jsonObject), tool -> builder.tools(tool));

		return builder.build();
	}

	protected abstract ChatLanguageModel getChatLanguageModel(
		JSONObject jsonObject);

	protected List<ChatModelListener> getChatModelListeners(
		JSONArray jsonArray) {

		List<ChatModelListener> chatModelListeners = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ChatModelListener chatModelListener =
				chatModelListenerProvider.getChatModelListener(
					jsonArray.getString(i));

			if (chatModelListener != null) {
				chatModelListeners.add(chatModelListener);
			}
		}

		return chatModelListeners;
	}

	protected String getMemoryId(AITaskContext aiTaskContext, String id) {
		return StringBundler.concat(
			aiTaskContext.getCompanyId(), StringPool.POUND,
			aiTaskContext.getUserId(), StringPool.POUND,
			aiTaskContext.getAITaskExternalReferenceCode(), StringPool.POUND,
			id);
	}

	protected List<Object> getTools(JSONObject jsonObject) {
		JSONObject toolsJSONObject = jsonObject.getJSONObject("tools");

		if (toolsJSONObject == null) {
			return Collections.emptyList();
		}

		List<Object> aiTaskTools = new ArrayList<>();

		for (String key : toolsJSONObject.keySet()) {
			Object aiTaskTool = aiToolsProvider.getTool(
				toolsJSONObject.getJSONObject(key), key);

			if (aiTaskTool != null) {
				aiTaskTools.add(aiTaskTool);
			}
		}

		return aiTaskTools;
	}

	protected void setChatMemoryProvider(
		AiServices<AIChatAssistant> builder, JSONObject jsonObject) {

		if (!jsonObject.getBoolean("useChatMemory")) {
			return;
		}

		builder.chatMemoryProvider(
			memoryId -> MessageWindowChatMemory.builder(
			).id(
				memoryId
			).maxMessages(
				jsonObject.getInt("memoryMaxMessages", 10)
			).chatMemoryStore(
				chatMemoryStoreProvider.getChatMemoryStore()
			).build());
	}

	@Reference
	protected AITasksConfigurationProvider aiTasksConfigurationProvider;

	@Reference
	protected AIToolsProvider aiToolsProvider;

	@Reference
	protected ChatMemoryStoreProvider chatMemoryStoreProvider;

	@Reference
	protected ChatModelListenerProvider chatModelListenerProvider;

	private ContentRetriever _createGoogleCustomWebSearchContentRetriever(
		JSONObject jsonObject) {

		GoogleCustomWebSearchEngine.GoogleCustomWebSearchEngineBuilder
			googleCustomWebSearchEngineBuilder =
				GoogleCustomWebSearchEngine.builder();

		SetterUtil.setNotBlankString(
			googleCustomWebSearchEngineBuilder::apiKey,
			jsonObject.getString("apiKey"));
		SetterUtil.setNotBlankString(
			googleCustomWebSearchEngineBuilder::csi,
			jsonObject.getString("csi"));

		googleCustomWebSearchEngineBuilder.includeImages(
			jsonObject.getBoolean("includeImage"));
		googleCustomWebSearchEngineBuilder.logRequests(
			jsonObject.getBoolean("logRequests"));
		googleCustomWebSearchEngineBuilder.logResponses(
			jsonObject.getBoolean("logResponses"));

		SetterUtil.setNotNullInteger(
			googleCustomWebSearchEngineBuilder::maxRetries, jsonObject,
			"maxRetries");

		if (jsonObject.has("timeout")) {
			googleCustomWebSearchEngineBuilder.timeout(
				Duration.ofSeconds(jsonObject.getInt("timeout")));
		}

		return WebSearchContentRetriever.builder(
		).webSearchEngine(
			googleCustomWebSearchEngineBuilder.build()
		).maxResults(
			jsonObject.getInt("maxResults", 3)
		).build();
	}

	private Map<String, Object> _getDebugInfo(
		boolean debug, Result<?> result, String systemMessage,
		String userMessage) {

		if (!debug || (result == null)) {
			return null;
		}

		return HashMapBuilder.<String, Object>put(
			"systemMessage", systemMessage
		).put(
			"userMessage", userMessage
		).putAll(
			getCommonDebugInfo(result.finishReason(), result.tokenUsage())
		).build();
	}

	private RetrievalAugmentor _getWebSearchRetrievalAugmentor(
		JSONObject jsonObject) {

		List<ContentRetriever> contentRetrievers = new ArrayList<>();

		for (String key : jsonObject.keySet()) {

			// Currently Custom Google engine only

			if (!StringUtil.equals("google-custom", key)) {
				continue;
			}

			contentRetrievers.add(
				_createGoogleCustomWebSearchContentRetriever(
					jsonObject.getJSONObject(key)));
		}

		if (contentRetrievers.isEmpty()) {
			return null;
		}

		return DefaultRetrievalAugmentor.builder(
		).queryRouter(
			new DefaultQueryRouter(
				contentRetrievers.toArray(new ContentRetriever[0]))
		).build();
	}

}