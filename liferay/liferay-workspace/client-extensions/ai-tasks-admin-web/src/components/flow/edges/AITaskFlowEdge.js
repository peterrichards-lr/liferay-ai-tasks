/**
 * @author Louis-Guillaume Durand
 */
import React from 'react';

import { BaseEdge, EdgeLabelRenderer, getBezierPath, useReactFlow } from '@xyflow/react';

import Icon from '../../ui/Icon';

const AITaskFlowEdge = ({
  id,
  sourceX,
  sourceY,
  targetX,
  targetY,
  sourcePosition,
  targetPosition,
}) => {
  const { setEdges } = useReactFlow();
  const [edgePath, labelX, labelY] = getBezierPath({
    sourceX,
    sourceY,
    sourcePosition,
    targetX,
    targetY,
    targetPosition,
  });

  return (
    <>
      <BaseEdge
        id={id}
        path={edgePath}
        style={{
          strokeWidth: 2,
          stroke: '#80acff',
        }}
      />
      <EdgeLabelRenderer>
        <button
          style={{
            background: '#80acff',
            color: '#fff',
            position: 'absolute',
            transform: `translate(-50%, -50%) translate(${labelX}px,${labelY}px)`,
            pointerEvents: 'all',
          }}
          className="nodrag nopan badge badge-secondary p-2"
          onClick={() => {
            setEdges((es) => es.filter((e) => e.id !== id));
          }}
        >
          <span className="badge-item badge-item-expand">
            <Icon name={'times'} />
          </span>
        </button>
      </EdgeLabelRenderer>
    </>
  );
};

export default AITaskFlowEdge;
