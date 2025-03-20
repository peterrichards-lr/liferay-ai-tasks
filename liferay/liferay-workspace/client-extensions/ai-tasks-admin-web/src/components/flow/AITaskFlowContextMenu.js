/**
 * @author Louis-Guillaume Durand
 */
import React, { useCallback } from 'react';

import { useReactFlow } from '@xyflow/react';

import { useAITasksContext } from '../../contexts/AITasksContext';
import { useNodeMenu } from '../../contexts/NodeMenuContext';
import Icon from '../ui/Icon';

const AITaskFlowContextMenu = ({
  id,
  handleGraphUpdate,
  onRename,
  onConfigure,
  onSetCondition,
  ...props
}) => {
  const { setNodes, setEdges, getNodes, getEdges } = useReactFlow();
  const { selectedNode, selectedTask } = useAITasksContext();
  const { isMenuOpen, menuPosition, setIsMenuOpen } = useNodeMenu();

  const deleteNode = useCallback(
    (e) => {
      e.preventDefault();
      const shouldDelete = confirm(`Are you sure you want to delete this node?`);
      if (shouldDelete) {
        const newNodes = getNodes().filter((node) => node.id !== selectedNode?.id);
        const newEdges = getEdges().filter((edge) => edge.source !== selectedNode?.id);
        setNodes(newNodes);
        setEdges(newEdges);
        handleGraphUpdate(newNodes, newEdges);
        setIsMenuOpen(false);
      }
    },
    [selectedNode?.id, setNodes, setEdges],
  );

  return (
    <div
      style={{
        top: menuPosition.top - 105,
        left: menuPosition.left - 10,
      }}
      className={isMenuOpen ? 'dropdown-menu show' : 'dropdown-menu'}
      {...props}
    >
      <ul className="list-unstyled">
        <li>
          <a className="dropdown-item" onClick={onRename}>
            <Icon name="pencil" />
            <span className={'ml-2'}>Rename</span>
          </a>
        </li>
        <li>
          <a className="dropdown-item" onClick={onConfigure}>
            <Icon name="control-panel" />
            <span className={'ml-2'}>Configure</span>
          </a>
        </li>
        {selectedNode?.id !== selectedTask.configuration.startNodeId && (
          <li>
            <a className="dropdown-item" onClick={onSetCondition}>
              <Icon name="merge" />
              <span className={'ml-2'}>Set Condition</span>
            </a>
          </li>
        )}
        <li>
          <a className="dropdown-item" href="#" onClick={deleteNode}>
            <Icon name="trash" />
            <span className={'ml-2'}>Delete</span>
          </a>
        </li>
      </ul>
    </div>
  );
};

export default AITaskFlowContextMenu;
