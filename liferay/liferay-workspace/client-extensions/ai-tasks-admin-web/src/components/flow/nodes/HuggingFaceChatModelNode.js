import React, { memo } from 'react';

import { Handle } from '@xyflow/react';

import { useNodeMenu } from '../../../contexts/NodeMenuContext';
import Icon from '../../ui/Icon';

const HuggingFaceChatModelNode = ({ id, data }) => {
  const { label, parameters } = data;
  const { toggleDropdown } = useNodeMenu();

  return (
    <div className="hugging-face-chat-model-node">
      <Handle type="target" position="left" />
      <div className={'node-header-container'}>
        <div className={'d-flex flex-row justify-content-between'}>
          <h3 className={'node-header-label'}>{label}</h3>
          <button
            className={'btn btn-monospaced btn-secondary btn-xs ml-4'}
            onClick={(event) => {
              toggleDropdown(event, id);
            }}
          >
            <Icon name={'ellipsis-v'} />
          </button>
        </div>
        <span className="label label-info">
          <span className="label-item label-item-expand">HuggingFace Chat Model</span>
        </span>
      </div>
      <div className="node-details-container">
        <div className="d-flex flex-row justify-content-between align-items-center">
          <span className={'detail-label'}>Model</span>
          <span className={'detail-value'}>{parameters.modelId}</span>
        </div>
        <div className="d-flex flex-row justify-content-between align-items-center">
          <span className={'detail-label'}>Temperature</span>
          <span className={'detail-value'}>{parameters.temperature}</span>
        </div>
      </div>
      <Handle type="source" position="right" />
    </div>
  );
};

export default memo(HuggingFaceChatModelNode);
