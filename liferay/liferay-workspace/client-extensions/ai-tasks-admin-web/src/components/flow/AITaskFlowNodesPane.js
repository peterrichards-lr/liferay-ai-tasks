/**
 * @author Louis-Guillaume Durand
 */
import React from 'react';

import {
  getImageGenerationNodes,
  getTextGenerationNodes,
  getUtilityNodes,
} from '../../constants/AITasksNodeTypesConstants';
import { useDnD } from '../../contexts/DnDContext';
import Icon from '../ui/Icon';

const AITaskFlowNodesPane = ({ isOpen, setIsOpen }) => {
  const [_, setType] = useDnD();

  const onDragStart = (event, nodeType) => {
    setType(nodeType);
    event.dataTransfer.effectAllowed = 'move';
  };

  return (
    <div
      className={
        'flow-nodes-pane contextual-sidebar sidebar-light sidebar-sm contextual-sidebar-visible ' +
        (isOpen ? 'flow-nodes-pane-open' : 'flow-nodes-pane-close')
      }
    >
      <div className="sidebar-header">
        <span className="d-flex flex-row justify-content-between text-truncate-inline">
          <span className="component-title text-truncate">Add Nodes</span>
          <button
            className={'btn btn-default'}
            onClick={(e) => {
              e.preventDefault();
              setIsOpen(false);
            }}
          >
            <Icon name={'times'} />
          </button>
        </span>
      </div>
      <div className="container">
        <div className="flow-nodes-pane-area d-flex flex-column">
          <h3 className={'node-category-title'}>Text Generation</h3>
          {getTextGenerationNodes.map((node, index) => {
            return (
              <div
                key={index}
                className={'new-node'}
                onDragStart={(event) => onDragStart(event, node.id)}
                draggable
              >
                <div
                  className={
                    'node-header-container d-flex flex-row align-items-center justify-content-start'
                  }
                >
                  <h4>{node.name}</h4>
                </div>
              </div>
            );
          })}
          <h3 className={'node-category-title'}>Image Generation</h3>
          {getImageGenerationNodes.map((node, index) => {
            return (
              <div
                key={index}
                className={'new-node'}
                onDragStart={(event) => onDragStart(event, node.id)}
                draggable
              >
                <div
                  className={
                    'node-header-container d-flex flex-row align-items-center justify-content-start'
                  }
                >
                  <h4>{node.name}</h4>
                </div>
              </div>
            );
          })}
          <h3 className={'node-category-title'}>Utility</h3>
          {getUtilityNodes.map((node, index) => {
            return (
              <div
                key={index}
                className={'new-node'}
                onDragStart={(event) => onDragStart(event, node.id)}
                draggable
              >
                <div
                  className={
                    'node-header-container d-flex flex-row align-items-center justify-content-start'
                  }
                >
                  <h4>{node.name}</h4>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
};

export default AITaskFlowNodesPane;
