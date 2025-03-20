/**
 * @author Louis-Guillaume Durand
 */
import React, { createContext, useContext, useState } from 'react';

import { useAITasksContext } from './AITasksContext';

const NodeMenuContext = createContext({
  isMenuOpen: false,
  setIsMenuOpen: (newState) => {},
  menuPosition: { top: 0, right: 0, bottom: 0, left: 0 },
  toggleDropdown: (event, id) => {},
});

export const NodeMenuProvider = ({ children }) => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [menuPosition, setMenuPosition] = useState({ top: 0, right: 0, bottom: 0, left: 0 });
  const { setSelectedNode, selectedTask } = useAITasksContext();

  const toggleDropdown = (event, id) => {
    const newMenuState = !isMenuOpen;
    setIsMenuOpen(newMenuState);
    setMenuPosition({
      top: event.target.getBoundingClientRect().top,
      left: event.target.getBoundingClientRect().left,
    });
    if (newMenuState) {
      const currentNode = selectedTask.configuration.nodes.find((node) => node.id === id);
      setSelectedNode(currentNode);
    }
  };

  return (
    <NodeMenuContext.Provider value={{ isMenuOpen, setIsMenuOpen, menuPosition, toggleDropdown }}>
      {children}
    </NodeMenuContext.Provider>
  );
};

export default NodeMenuContext;

export const useNodeMenu = () => {
  return useContext(NodeMenuContext);
};
