/**
 * @author Louis-Guillaume Durand
 */
import React, { useEffect, useState } from 'react';

import { useAITasksContext } from '../../contexts/AITasksContext';

const AITaskFlowNodeRename = () => {
  const { selectedNode, setSelectedNode } = useAITasksContext();
  const [currentNode, setCurrentNode] = useState(selectedNode);

  useEffect(() => {
    setCurrentNode(selectedNode);
  }, [selectedNode]);

  if (!selectedNode) {
    return null;
  }

  return (
    <div className={'form-row'}>
      <div className="form-group col-12">
        <label htmlFor={'label'}>Label</label>
        <input
          id={'label'}
          value={currentNode.label}
          type={'text'}
          className={'form-control'}
          onChange={(e) => {
            const newNode = {
              ...currentNode,
              label: e.target.value,
            };
            setCurrentNode(newNode);
            setSelectedNode(newNode);
          }}
        />
      </div>
    </div>
  );
};

export default AITaskFlowNodeRename;
