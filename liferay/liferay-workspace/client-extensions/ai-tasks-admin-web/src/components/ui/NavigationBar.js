/**
 * @author Louis-Guillaume Durand
 */
import React from 'react';

const NavigationBar = ({ children }) => {
  const childrenArray = React.Children.toArray(children);
  const left = childrenArray.filter((child) => child.props.slot === 'left');
  const center = childrenArray.filter((child) => child.props.slot === 'center');
  const right = childrenArray.filter((child) => child.props.slot === 'right');

  return (
    <nav className="ai-tasks-navbar management-bar navbar navbar-expand-md fixed-top justify-content-space-between management-bar-light">
      <div className={'container-fluid container-fluid-max-xl'}>
        <ul className={'navbar-nav align-items-center'}>{left}</ul>
        <div className={'navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down'}>
          <div className={'w-100 pr-4'}>{center}</div>
        </div>
        <ul className={'navbar-nav'}>{right}</ul>
      </div>
    </nav>
  );
};

export default NavigationBar;
