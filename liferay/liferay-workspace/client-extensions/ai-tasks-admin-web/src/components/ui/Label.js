/**
 * @author Louis-Guillaume Durand
 */
import React from 'react';

const Label = ({ type, className, children }) => (
  <span className={'label label-' + type + ' ' + className}>
    <span className="label-item label-item-expand">{children}</span>
  </span>
);

export default Label;
