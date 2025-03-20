/**
 * @author Louis-Guillaume Durand
 */
import React, { createContext, useContext, useState } from 'react';

import Modal from '../components/ui/Modal';

const ModalContext = createContext({
  isModalOpen: false,
  setIsModalOpen: (newState) => {},
  setModalTitle: (title) => {},
  setModalContent: (content) => {},
  setModalFooter: (content) => {},
});

export const ModalProvider = ({ children }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [modalTitle, setModalTitle] = useState(null);
  const [modalContent, setModalContent] = useState(null);
  const [modalFooter, setModalFooter] = useState(null);

  return (
    <ModalContext.Provider
      value={{
        isModalOpen,
        setIsModalOpen,
        setModalTitle,
        setModalContent,
        setModalFooter,
      }}
    >
      <div className={isModalOpen ? 'modal-open' : ''}>
        {children}
        <Modal title={modalTitle} content={modalContent} footer={modalFooter} />
        <div className={'modal-backdrop fade modal' + (isModalOpen ? ' show' : '')}></div>
      </div>
    </ModalContext.Provider>
  );
};

export default ModalContext;

export const useModal = () => {
  return useContext(ModalContext);
};
