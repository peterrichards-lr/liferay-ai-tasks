/**
 * @author Louis-Guillaume Durand
 * @author Petteri Karttunen
 */

export const ANTHROPIC_CHAT_MODEL = 'anthropicChatModel';
export const ENTRY_POINT_NODE = 'entryPointNode';
export const GEMINI_CHAT_MODEL = 'geminiChatModel';
export const GOOGLE_IMAGEN = 'googleImagen';
export const HUGGING_FACE_CHAT_MODEL = 'huggingFaceChatModel';
export const LIFERAY_SEARCH = 'LiferaySearchAITaskNode';
export const MISTRALAI_CHAT_MODEL = 'mistralAIChatModel';
export const OLLAMA_CHAT_MODEL = 'ollamaChatModel';
export const OPENAI_CHAT_MODEL = 'openAIChatModel';
export const OPENAI_IMAGE_MODEL = 'openAIImageModel';
export const WEBHOOK = 'webhook';

export const getTextGenerationNodes = [
  { id: ANTHROPIC_CHAT_MODEL, name: 'Anthropic Chat Model' },
  { id: GEMINI_CHAT_MODEL, name: 'Gemini Chat Model' },
  { id: HUGGING_FACE_CHAT_MODEL, name: 'Hugging Face Chat Model' },
  { id: MISTRALAI_CHAT_MODEL, name: 'Mistral AI Chat Model' },
  { id: OLLAMA_CHAT_MODEL, name: 'Ollama Chat Model' },
  { id: OPENAI_CHAT_MODEL, name: 'OpenAI Chat Model' },
];

export const getImageGenerationNodes = [
  { id: GOOGLE_IMAGEN, name: 'Google Imagen' },
  {
    id: OPENAI_IMAGE_MODEL,
    name: 'OpenAI Image Model',
  },
];

export const getUtilityNodes = [
  { id: LIFERAY_SEARCH, name: 'Liferay Search' },
  { id: WEBHOOK, name: 'Webhook' },
];
