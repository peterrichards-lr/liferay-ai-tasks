/**
 * @author Louis-Guillaume Durand
 */
import React, { memo } from 'react';

import { Handle } from '@xyflow/react';

const EntryPointNode = ({ data }) => {
  const { label } = data;
  return (
    <div className="start-node">
      <div
        className={
          'node-header-container d-flex flex-row align-items-center justify-content-center'
        }
      >
        <h3>{label}</h3>
      </div>
      <Handle type="source" position="right" />
    </div>
  );
};

export default memo(EntryPointNode);
