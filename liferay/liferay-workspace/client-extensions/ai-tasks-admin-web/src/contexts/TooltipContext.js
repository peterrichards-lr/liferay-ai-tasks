/**
 * @author Louis-Guillaume Durand
 */
import React, { createContext, useContext, useState } from 'react';

const TooltipContext = createContext({
  isTooltipOpen: false,
  setIsTooltipOpen: () => {},
  setTooltipContent: () => {},
  setTooltipPosition: () => {},
  setTooltipPositionClass: () => {},
});

export const TooltipProvider = ({ children }) => {
  const [isTooltipOpen, setIsTooltipOpen] = useState(false);
  const [tooltipContent, setTooltipContent] = useState(null);
  const [tooltipPositionClass, setTooltipPositionClass] = useState('bottom-left');
  const [tooltipPosition, setTooltipPosition] = useState({
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
  });

  return (
    <TooltipContext.Provider
      value={{
        isTooltipOpen,
        setIsTooltipOpen,
        setTooltipContent,
        setTooltipPosition,
        setTooltipPositionClass,
      }}
    >
      {children}
      <div
        className={'tooltip clay-tooltip-' + tooltipPositionClass + (isTooltipOpen ? ' show' : '')}
        role="tooltip"
        style={{
          height: isTooltipOpen ? '100%' : 0,
          width: isTooltipOpen ? '100%' : 0,
          top: isTooltipOpen ? tooltipPosition.top : 0,
          left: isTooltipOpen ? tooltipPosition.left : 0,
          right: isTooltipOpen ? tooltipPosition.right : 0,
          bottom: isTooltipOpen ? tooltipPosition.bottom : 0,
        }}
      >
        <div className="arrow"></div>
        <div className="tooltip-inner">{tooltipContent}</div>
      </div>
    </TooltipContext.Provider>
  );
};

export const useTooltip = () => {
  return useContext(TooltipContext);
};
