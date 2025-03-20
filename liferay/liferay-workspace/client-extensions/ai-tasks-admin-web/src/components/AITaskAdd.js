/**
 * @author Louis-Guillaume Durand
 */
import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';

import { v4 as uuidv4 } from 'uuid';

import { ROUTE_TASK_LIST } from '../constants/AITasksRoutesConstants';
import { useAITasksContext } from '../contexts/AITasksContext';
import Icon from './ui/Icon';
import NavigationBar from './ui/NavigationBar';

const AITaskAdd = () => {
  const { addTask } = useAITasksContext();
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    externalReferenceCode: uuidv4(),
    configuration: {
      debug: true,
      edges: [],
      nodes: [],
    },
    enabled: true,
  });
  const [errors, setErrors] = useState({
    title: '',
    description: '',
    externalReferenceCode: '',
  });

  const handleChange = (event) => {
    let { name, value } = event.target;
    if (name === 'externalReferenceCode') {
      value = value.trim().replaceAll(' ', '');
    }
    if (name === 'enabled') {
      value = !formData.enabled;
    }
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
    setErrors((prevErrors) => ({ ...prevErrors, [name]: '' }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    let hasErrors = false;
    if (!formData.title) {
      setErrors((prevErrors) => ({ ...prevErrors, title: 'This field is required' }));
      hasErrors = true;
    }
    if (!formData.externalReferenceCode) {
      setErrors((prevErrors) => ({
        ...prevErrors,
        externalReferenceCode: 'This field is required',
      }));
      hasErrors = true;
    }
    if (hasErrors) {
      return;
    }
    addTask(formData);
  };

  return (
    <>
      <NavigationBar></NavigationBar>
      <div
        className={'container-fluid container-fluid-max-lg container-form-lg sheet sheet-xl mt-7'}
      >
        <form onSubmit={handleSubmit}>
          <div className={'form-group ' + (errors.title && errors.title.length > 0 && 'has-error')}>
            <label htmlFor="title">
              Title
              <Icon name={'asterisk'} className={'reference-mark'} />
            </label>
            <input
              className="form-control"
              id="title"
              name="title"
              placeholder="Untitled AI Task"
              type="text"
              value={formData.title}
              onChange={handleChange}
            />
            {errors.title && errors.title.length > 0 && (
              <div className="form-feedback-group">
                <div className="form-feedback-item" id="title-error-message">
                  {errors.title}
                </div>
              </div>
            )}
          </div>

          <div
            className={
              'form-group ' +
              (errors.externalReferenceCode &&
                errors.externalReferenceCode.length > 0 &&
                'has-error')
            }
          >
            <label htmlFor="externalReferenceCode">
              External Reference Code
              <Icon name={'asterisk'} className={'reference-mark'} />
            </label>
            <input
              className="form-control"
              id="externalReferenceCode"
              name="externalReferenceCode"
              placeholder="b47de16a-c595-4453-9bef-8959afc28fd5"
              type="text"
              value={formData.externalReferenceCode}
              onChange={handleChange}
            />
            {errors.externalReferenceCode && errors.externalReferenceCode.length > 0 && (
              <div className="form-feedback-group">
                <div className="form-feedback-item" id="externalReferenceCode-error-message">
                  {errors.externalReferenceCode}
                </div>
              </div>
            )}
          </div>
          <div className="form-group">
            <label htmlFor="description">Description</label>
            <textarea
              className="form-control"
              id="description"
              name="description"
              rows="3"
              onChange={handleChange}
              value={formData.description}
            />
          </div>
          <div className="form-group">
            <label className="toggle-switch">
              <span className="toggle-switch-label">
                {formData.enabled ? 'Enabled' : 'Disabled'}
              </span>
              <span className="toggle-switch-check-bar">
                <input
                  id="enabled"
                  name="enabled"
                  className="toggle-switch-check"
                  role="switch"
                  type="checkbox"
                  value={formData.enabled}
                  onChange={handleChange}
                  defaultChecked={formData.enabled}
                />
                <span aria-hidden="true" className="toggle-switch-bar">
                  <span className="toggle-switch-handle"></span>
                </span>
              </span>
            </label>
          </div>
          <button className={'btn btn-primary'} type={'submit'}>
            Save
          </button>
          <NavLink to={ROUTE_TASK_LIST} className={'btn btn-secondary ml-2'}>
            Cancel
          </NavLink>
        </form>
      </div>
    </>
  );
};

export default AITaskAdd;
