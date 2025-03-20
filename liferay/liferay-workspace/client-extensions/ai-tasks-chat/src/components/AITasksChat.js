import React from 'react';
import ChatBot from 'react-chatbotify';

import LiferayService from '../services/LiferayService';

const AITasksChat = () => {
  const settings = {
    general: { embedded: false },
    header: {
      showAvatar: true,
      avatar: 'https://randomuser.me/api/portraits/men/35.jpg',
      title: 'Joe Bloggs',
    },
    chatHistory: { storageKey: `ai-tasks-chat` },
  };

  const themes = [{ id: 'minimal_midnight', version: '0.1.0' }];

  let erc = null;
  let hasError = false;

  const getTask = async (params) => {
    try {
      await LiferayService.get(`/o/ai-tasks/v1.0/ai-tasks/by-external-reference-code/${erc}`);
    } catch (error) {}
  };

  const executeTask = async (params) => {
    try {
      const response = await LiferayService.post(`/o/ai-tasks/v1.0/generate/${erc}`, {
        input: {
          text: params.userInput,
        },
      });

      const answer =
        response.output.text ||
        response.output.error ||
        `Sorry, it seems that I don't have a message to deliver.`;
      await params.injectMessage(answer);
    } catch (error) {
      await params.injectMessage(error);
      hasError = true;
    }
  };

  const flow = {
    start: {
      message: 'Enter the external reference code of your AI task and start asking away!',
      path: 'erc',
    },
    retry: {
      message: `No AI task found with this external reference code.`,
      path: 'erc',
    },
    erc: {
      transition: { duration: 0 },
      chatDisabled: true,
      path: async (params) => {
        erc = params.userInput.trim();
        try {
          await LiferayService.get(`/o/ai-tasks/v1.0/ai-tasks/by-external-reference-code/${erc}`);
          return 'beforeLoop';
        } catch (error) {
          return 'retry';
        }
      },
    },
    beforeLoop: {
      message: `Ok, let's start. Ask me anything!`,
      path: 'loop',
    },
    loop: {
      message: async (params) => {
        await executeTask(params);
      },
      path: () => {
        if (hasError) {
          return 'start';
        }
        return 'loop';
      },
    },
  };
  return <ChatBot settings={settings} flow={flow} themes={themes} />;
};

export default AITasksChat;
