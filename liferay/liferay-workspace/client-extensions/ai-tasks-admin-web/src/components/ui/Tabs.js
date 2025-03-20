/**
 * @author Louis-Guillaume Durand
 */
import React, { Children, cloneElement, useState } from 'react';

const Tab = ({ id, label, className, children }) => {
  return { id, label, className, children };
};

const Tabs = ({ children, className }) => {
  const [activeTab, setActiveTab] = useState(0);

  const handleClick = (index) => {
    setActiveTab(index);
  };

  return (
    <>
      <ul className={`nav nav-tabs ${className}`} role="tablist">
        {Children.map(children, (child, index) => (
          <li key={index} className="nav-item">
            <a
              aria-controls={child.props.id}
              aria-selected={activeTab === index ? 'true' : 'false'}
              className={`nav-link ${activeTab === index ? 'active' : ''}`}
              data-toggle="tab"
              href={`#${child.props.id}`}
              id={`${child.props.id}-tab`}
              role="tab"
              onClick={(e) => {
                e.preventDefault();
                handleClick(index);
              }}
            >
              {child.props.label}
            </a>
          </li>
        ))}
      </ul>
      <div className="tab-content">
        {Children.map(children, (child, index) => {
          if (!child.props.children) {
            return null;
          }
          return cloneElement(child.props.children, {
            className: `${
              child.props.className === undefined ? '' : child.props.className + ' '
            }fade tab-pane ${activeTab === index ? 'active show' : ''}`,
            'aria-labelledby': `${child.props.id}-tab`,
            id: child.props.id,
            role: 'tabpanel',
          });
        })}
      </div>
    </>
  );
};

export { Tabs, Tab };
