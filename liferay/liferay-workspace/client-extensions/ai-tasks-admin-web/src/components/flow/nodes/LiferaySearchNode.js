/**
 * @author Louis-Guillaume Durand
 * @author Petteri Karttunen
 */
import React, { memo } from 'react';

import { Handle } from '@xyflow/react';

import { useNodeMenu } from '../../../contexts/NodeMenuContext';
import Icon from '../../ui/Icon';

const LiferaySearchNode = ({ id, data }) => {
  const { label, parameters } = data;
  const { toggleDropdown } = useNodeMenu();

  return (
    <div className="liferay-search-node">
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
        <span className="label label-warning">
          <span className="label-item label-item-expand">Liferay Search</span>
        </span>
      </div>
      <div className="node-details-container">
        <div className="d-flex flex-row justify-content-between align-items-center">
          <span className={'detail-label'}>Blueprint</span>
          <span className={'detail-value'}>{parameters.sxpBlueprintExternalReferenceCode}</span>
        </div>
        <div className="d-flex flex-row justify-content-between align-items-center">
          <span className={'detail-label'}>Output</span>
          <span className={'detail-value'}>{parameters.taskContextOutputParameterName}</span>
        </div>
      </div>
      <Handle type="source" position="right" />
    </div>
  );
};

export default memo(LiferaySearchNode);
