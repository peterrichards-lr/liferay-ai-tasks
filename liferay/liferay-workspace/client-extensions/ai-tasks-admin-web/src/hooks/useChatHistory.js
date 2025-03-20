/**
 * @author Louis-Guillaume Durand
 */
import { useEffect, useState } from 'react';

function useChatHistory(taskId) {
  const chatKey = `${taskId}-chat-preview-history`;
  const [history, setHistory] = useState(() => {
    const storedHistory = localStorage.getItem(chatKey);
    return storedHistory ? JSON.parse(storedHistory) : [];
  });

  useEffect(() => {
    localStorage.setItem(chatKey, JSON.stringify(history));
  }, [chatKey, history]);

  const addMessage = (text, role, debug, think) => {
    const newMessage = { text, role, debug, think };
    setHistory((prevHistory) => [...prevHistory, newMessage]);
  };

  const clearHistory = () => {
    setHistory([]);
    localStorage.removeItem(chatKey);
  };

  return { history, addMessage, clearHistory };
}

export default useChatHistory;
