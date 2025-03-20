/**
 * @author Louis-Guillaume Durand
 */
import React, { useEffect, useState } from 'react';

import { useAITasksContext } from '../../contexts/AITasksContext';

const AITaskFlowNodeSetCondition = () => {
  const { selectedNode, setSelectedNode } = useAITasksContext();
  const [currentNode, setCurrentNode] = useState(selectedNode);

  useEffect(() => {
    setCurrentNode(selectedNode);
  }, [selectedNode]);

  if (!selectedNode) {
    return null;
  }

  return (
    <div className="form-group">
      <div className="input-group">
        <div className="input-group-item input-group-prepend">
          <input
            id={'conditionField'}
            placeholder={'{{taskContext.outputOne}}'}
            value={currentNode.condition?.equals?.field}
            type={'text'}
            className={'form-control'}
            onChange={(e) => {
              e.preventDefault();
              const newNode = {
                ...currentNode,
                condition: {
                  equals: {
                    ...currentNode.condition?.equals,
                    id: `${currentNode.id}Condition`,
                    field: e.target.value,
                  },
                },
              };
              setCurrentNode(newNode);
              setSelectedNode(newNode);
            }}
          />
        </div>
        <div className="input-group-item input-group-item-shrink input-group-prepend">
          <span className="input-group-text">=</span>
        </div>
        <div className="input-group-item input-group-prepend">
          <input
            id={'conditionValue'}
            placeholder={'someValue'}
            value={currentNode.condition?.equals?.value}
            type={'text'}
            className={'form-control'}
            onChange={(e) => {
              e.preventDefault();
              const newNode = {
                ...currentNode,
                condition: {
                  equals: {
                    ...currentNode.condition?.equals,
                    id: `${currentNode.id}Condition`,
                    value: e.target.value,
                  },
                },
              };
              setCurrentNode(newNode);
              setSelectedNode(newNode);
            }}
          />
        </div>
      </div>
    </div>
  );
};

export default AITaskFlowNodeSetCondition;
