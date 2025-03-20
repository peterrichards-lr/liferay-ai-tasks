/**
 * @author Louis-Guillaume Durand
 */
import React from 'react';

import { useModal } from '../../contexts/ModalContext';
import Icon from './Icon';

const Modal = ({ title, content, footer }) => {
  const { isModalOpen, setIsModalOpen } = useModal();

  return (
    <div
      aria-labelledby="aiTasksModalLabel"
      className={'fade modal' + (isModalOpen ? ' show' : '')}
      id="aiTasksModal"
      role="dialog"
      tabIndex="-1"
    >
      <div className="modal-dialog modal-lg">
        <div className="modal-content">
          <div className="modal-header">
            <div className="modal-title" id="aiTasksModalLabel">
              {title}
            </div>
            <button
              aria-label="Close"
              title="Close"
              className="close"
              data-dismiss="modal"
              role="button"
              type="button"
              onClick={(e) => {
                e.preventDefault();
                setIsModalOpen(false);
              }}
            >
              <Icon name={'times'} />
            </button>
          </div>
          <div className="modal-body">{content}</div>
          <div className="modal-footer">
            <div className="modal-item-last">{footer}</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Modal;
