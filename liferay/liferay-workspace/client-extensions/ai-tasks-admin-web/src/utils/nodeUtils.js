/**
 * @author Louis-Guillaume Durand
 */
import {
  ANTHROPIC_CHAT_MODEL,
  ENTRY_POINT_NODE,
  GEMINI_CHAT_MODEL,
  GOOGLE_IMAGEN,
  HUGGING_FACE_CHAT_MODEL,
  LIFERAY_SEARCH,
  MISTRALAI_CHAT_MODEL,
  OLLAMA_CHAT_MODEL,
  OPENAI_CHAT_MODEL,
  OPENAI_IMAGE_MODEL,
  WEBHOOK,
} from '../constants/AITasksNodeTypesConstants';

export const getDefaultParameters = (nodeType) => {
  if (nodeType === ANTHROPIC_CHAT_MODEL) {
    return {
      apiKey: 'env:ANTHROPIC_API_KEY',
      baseUrl: '',
      memoryMaxMessages: 20,
      modelName: 'claude-3-sonnet-20240229',
      outputParameterName: 'text',
      promptTemplate: '{{input.text}}',
      systemMessage: '',
      temperature: 0.7,
      timeout: 20,
      useChatMemory: true,
    };
  }
  if (nodeType === GEMINI_CHAT_MODEL) {
    return {
      location: '',
      memoryMaxMessages: 20,
      modelName: 'gemini-1.5-pro',
      maxOutputTokens: 350,
      outputParameterName: 'text',
      promptTemplate: '{{input.text}}',
      project: '',
      systemMessage: '',
      temperature: 0.7,
      timeout: 20,
      topK: 3,
      topP: 1,
      useChatMemory: true,
    };
  }
  if (nodeType === GOOGLE_IMAGEN) {
    return {
      endpoint: 'us-central1-aiplatform.googleapis.com:443',
      imageStyle: 'digitalArt',
      location: '',
      modelName: 'imagegeneration@005',
      outputParameterName: 'image',
      promptTemplate: '{{input.text}}',
      project: '',
      publisher: 'google',
      sampleImageSize: 1536,
    };
  }
  if (nodeType === HUGGING_FACE_CHAT_MODEL) {
    return {
      accessToken: '',
      baseUrl: '',
      maxNewTokens: '',
      modelName: '',
      outputParameterName: 'text',
      returnFullText: true,
      temperature: 0.7,
      timeout: 20,
      waitForModel: true,
    };
  }
  if (nodeType === LIFERAY_SEARCH) {
    return {
      topK: 3,
      inputParameterName: 'text',
      sxpBlueprintExternalReferenceCode: 'semantic_search',
      taskContextOutputParameterName: 'ragDocuments',
      documentResultField: 'content_{{taskContext.currentLanguageId}}',
    };
  }
  if (nodeType === MISTRALAI_CHAT_MODEL) {
    return {
      apiKey: 'env:MISTRALAI_API_KEY',
      baseUrl: '',
      memoryMaxMessages: 20,
      modelName: 'mistral-small-latest',
      outputParameterName: 'text',
      promptTemplate: '{{input.text}}',
      systemMessage: '',
      temperature: 0.7,
      timeout: 20,
      useChatMemory: true,
    };
  }
  if (nodeType === OLLAMA_CHAT_MODEL) {
    return {
      baseUrl: 'http://localhost:11434',
      memoryMaxMessages: 20,
      modelName: 'llama3.2',
      outputParameterName: 'text',
      promptTemplate: '{{input.text}}',
      systemMessage: '',
      temperature: 0.7,
      useChatMemory: true,
    };
  }
  if (nodeType === OPENAI_CHAT_MODEL) {
    return {
      apiKey: '',
      baseUrl: 'https://api.openai.com/v1/',
      memoryMaxMessages: 20,
      modelName: 'gpt-4o-mini',
      outputParameterName: 'text',
      promptTemplate: '{{input.text}}',
      systemMessage: '',
      temperature: 0.7,
      timeout: 20,
      useChatMemory: true,
    };
  }
  if (nodeType === OPENAI_IMAGE_MODEL) {
    return {
      apiKey: '',
      modelName: 'dall-e-2',
      outputParameterName: 'image',
      promptTemplate: '{{input.text}}',
      responseFormat: 'b64_json',
    };
  }
  if (nodeType === WEBHOOK) {
    return {
      method: 'POST',
      taskContextOutputParameterName: 'webhookResponse',
      url: '',
    };
  }
  return {};
};
