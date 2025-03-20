/**
 * @author Louis-Guillaume Durand
 */
import React from 'react';

import { useModal } from '../../contexts/ModalContext';

const ModalFooterButtonGroup = ({ onConfirm, onConfirmLabel }) => {
  const { setIsModalOpen } = useModal();
  return (
    <div className="btn-group">
      <div className="btn-group-item">
        <button
          className="btn btn-secondary"
          data-dismiss="modal"
          type="button"
          onClick={(e) => {
            e.preventDefault();
            setIsModalOpen(false);
          }}
        >
          Close
        </button>
      </div>
      <div className="btn-group-item">
        <button
          className="btn btn-primary"
          type="button"
          onClick={(e) => {
            e.preventDefault();
            onConfirm();
            setIsModalOpen(false);
          }}
        >
          {onConfirmLabel || 'Save'}
        </button>
      </div>
    </div>
  );
};

export default ModalFooterButtonGroup;
