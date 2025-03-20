/**
 * @author Louis-Guillaume Durand
 * @author Petteri Karttunen
 */
import React, { useEffect, useState } from 'react';

import {
  ANTHROPIC_CHAT_MODEL,
  GEMINI_CHAT_MODEL,
  GOOGLE_IMAGEN,
  HUGGING_FACE_CHAT_MODEL,
  LIFERAY_SEARCH,
  MISTRALAI_CHAT_MODEL,
  OLLAMA_CHAT_MODEL,
  OPENAI_CHAT_MODEL,
  OPENAI_IMAGE_MODEL,
  WEBHOOK,
} from '../../constants/AITasksNodeTypesConstants';
import { useAITasksContext } from '../../contexts/AITasksContext';
import AnthropicChatModelNodeConfigureForm from './forms/AnthropicChatModelNodeConfigureForm';
import GeminiChatModelNodeConfigureForm from './forms/GeminiChatModelNodeConfigureForm';
import GoogleImagenNodeConfigureForm from './forms/GoogleImagenNodeConfigureForm';
import HuggingFaceChatModelNodeConfigureForm from './forms/HuggingFaceChatModelNodeConfigureForm';
import LiferaySearchNodeConfigureForm from './forms/LiferaySearchNodeConfigureForm';
import MistralAIChatModelNodeConfigureForm from './forms/MistralAIChatModelNodeConfigureForm';
import OllamaChatModelNodeConfigureForm from './forms/OllamaChatModelNodeConfigureForm';
import OpenAIChatModelNodeConfigureForm from './forms/OpenAIChatModelNodeConfigureForm';
import OpenAIImageModelNodeConfigureForm from './forms/OpenAIImageModelNodeConfigureForm';
import WebhookNodeConfigureForm from './forms/WebhookNodeConfigureForm';

const AITaskFlowNodeConfigure = () => {
  const { selectedNode, setSelectedNode } = useAITasksContext();
  const [currentNode, setCurrentNode] = useState(selectedNode);

  const handleParameterChange = (key, value) => {
    const newNode = {
      ...currentNode,
      parameters: {
        ...currentNode.parameters,
        [key]: value,
      },
    };
    setCurrentNode(newNode);
    setSelectedNode(newNode);
  };

  useEffect(() => {
    setCurrentNode(selectedNode);
  }, [selectedNode]);

  if (!selectedNode) {
    return null;
  }

  if (currentNode.type === ANTHROPIC_CHAT_MODEL) {
    return (
      <AnthropicChatModelNodeConfigureForm
        nodeParameters={currentNode.parameters}
        onChange={handleParameterChange}
      />
    );
  }

  if (currentNode.type === GEMINI_CHAT_MODEL) {
    return (
      <GeminiChatModelNodeConfigureForm
        nodeParameters={currentNode.parameters}
        onChange={handleParameterChange}
      />
    );
  }

  if (currentNode.type === GOOGLE_IMAGEN) {
    return (
      <GoogleImagenNodeConfigureForm
        nodeParameters={currentNode.parameters}
        onChange={handleParameterChange}
      />
    );
  }

  if (currentNode.type === HUGGING_FACE_CHAT_MODEL) {
    return (
      <HuggingFaceChatModelNodeConfigureForm
        nodeParameters={currentNode.parameters}
        onChange={handleParameterChange}
      />
    );
  }

  if (currentNode.type === LIFERAY_SEARCH) {
    return (
      <LiferaySearchNodeConfigureForm
        nodeParameters={currentNode.parameters}
        onChange={handleParameterChange}
      />
    );
  }

  if (currentNode.type === MISTRALAI_CHAT_MODEL) {
    return (
      <MistralAIChatModelNodeConfigureForm
        nodeParameters={currentNode.parameters}
        onChange={handleParameterChange}
      />
    );
  }

  if (currentNode.type === OLLAMA_CHAT_MODEL) {
    return (
      <OllamaChatModelNodeConfigureForm
        nodeParameters={currentNode.parameters}
        onChange={handleParameterChange}
      />
    );
  }

  if (currentNode.type === OPENAI_CHAT_MODEL) {
    return (
      <OpenAIChatModelNodeConfigureForm
        nodeParameters={currentNode.parameters}
        onChange={handleParameterChange}
      />
    );
  }

  if (currentNode.type === OPENAI_IMAGE_MODEL) {
    return (
      <OpenAIImageModelNodeConfigureForm
        nodeParameters={currentNode.parameters}
        onChange={handleParameterChange}
      />
    );
  }
  if (currentNode.type === WEBHOOK) {
    return (
      <WebhookNodeConfigureForm
        nodeParameters={currentNode.parameters}
        onChange={handleParameterChange}
      />
    );
  }

  return null;
};

export default AITaskFlowNodeConfigure;
