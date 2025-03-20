import React from 'react';

import { useAITasksContext } from '../contexts/AITasksContext';
import ModalFooterButtonGroup from './ui/ModalFooterButtonGroup';

const AITaskEditModal = () => {
  const { selectedTask, setSelectedTask } = useAITasksContext();

  const handleInputOnChange = (e) => {
    let { name, value } = e.target;
    setSelectedTask({
      ...selectedTask,
      [name]: value,
    });
  };

  if (!selectedTask) {
    return null;
  }

  return (
    <>
      <div className={'form-group'}>
        <label htmlFor="title">Title</label>
        <input
          className="form-control"
          id="title"
          name="title"
          placeholder="Untitled AI Task"
          type="text"
          onChange={handleInputOnChange}
          value={selectedTask.title}
        />
      </div>
      <div className="form-group">
        <label htmlFor="description">Description</label>
        <textarea
          className="form-control"
          id="description"
          name="description"
          rows="3"
          onChange={handleInputOnChange}
          value={selectedTask.description}
        />
      </div>
    </>
  );
};

export const AITaskEditModalFooter = () => {
  const { selectedTask, updateTask } = useAITasksContext();
  return (
    <ModalFooterButtonGroup
      onConfirm={() => {
        updateTask(selectedTask);
      }}
    ></ModalFooterButtonGroup>
  );
};

export default AITaskEditModal;
