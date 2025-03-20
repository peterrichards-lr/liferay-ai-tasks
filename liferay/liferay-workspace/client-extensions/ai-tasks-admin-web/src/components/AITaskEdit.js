/**
 * @author Louis-Guillaume Durand
 */
import React, { useCallback, useEffect, useState } from 'react';
import { NavLink, useParams } from 'react-router-dom';

import { JsonEditor } from 'json-edit-react';

import { ROUTE_TASK_LIST } from '../constants/AITasksRoutesConstants';
import { useAITasksContext } from '../contexts/AITasksContext';
import { DnDProvider } from '../contexts/DnDContext';
import { useModal } from '../contexts/ModalContext';
import AITaskChatPreview from './AITaskChatPreview';
import AITaskEditModal, { AITaskEditModalFooter } from './AITaskEditModal';
import AITaskFlowEditor from './flow/AITaskFlowEditor';
import Alert from './ui/Alert';
import Icon from './ui/Icon';
import Label from './ui/Label';
import LoadingIndicator from './ui/LoadingIndicator';
import NavigationBar from './ui/NavigationBar';
import { Tab, Tabs } from './ui/Tabs';

const AITaskEdit = () => {
  const { fetchTask, selectedTask, setSelectedTask, updateTask, loading, error } =
    useAITasksContext();
  const [isChatPreviewOpen, setIsChatPreviewOpen] = useState(false);
  const { id } = useParams();
  const { setIsModalOpen, setModalTitle, setModalContent, setModalFooter } = useModal();

  const fetchCurrentTask = useCallback(async () => {
    const task = await fetchTask(id);
    setSelectedTask(task);
  }, [id]);

  useEffect(() => {
    fetchCurrentTask();
  }, []);

  const handleConfigurationChange = async ({ newData }) => {
    const updatedTask = {
      ...selectedTask,
      configuration: { ...newData },
    };
    updateTask(updatedTask);
  };

  const handleInputOnChange = (e) => {
    let { name, value } = e.target;
    if (name === 'externalReferenceCode') {
      value = value.trim().replaceAll(' ', '');
    }
    if (name === 'enabled') {
      value = JSON.parse(value);
    }
    const updatedTask = {
      ...selectedTask,
      [name]: value,
    };

    setSelectedTask(updatedTask);
  };

  const handleBasicInfoChanges = async () => {
    const currentSavedTask = await fetchTask(selectedTask.id);
    if (
      currentSavedTask.title !== selectedTask.title ||
      currentSavedTask.externalReferenceCode !== selectedTask.externalReferenceCode ||
      currentSavedTask.enabled !== selectedTask.enabled
    ) {
      updateTask(selectedTask);
    }
  };

  const openModal = (e) => {
    e.preventDefault();
    setModalTitle(`Edit Title and Description`);
    setModalContent(() => <AITaskEditModal />);
    setModalFooter(() => <AITaskEditModalFooter />);
    setIsModalOpen(true);
  };

  if (loading) {
    return (
      <div className={'d-flex justify-content-center align-items-center vh-100'}>
        <LoadingIndicator />
      </div>
    );
  }

  if (error) {
    return <Alert type={'danger'} lead={'Error'} message={error} />;
  }

  if (!selectedTask) {
    return <div>Item not found</div>;
  }

  return (
    <>
      <NavigationBar>
        <NavLink to={ROUTE_TASK_LIST} slot="left" className={'btn btn-monospaced btn-sm'}>
          <Icon name={'angle-left'} />
        </NavLink>
        <div slot="left" className={'border-right ml-sm-2 mr-3 pr-3'}>
          <Label type={'info'} className={'my-2'}>
            {'version ' + selectedTask.version}
          </Label>
          <div style={{ maxWidth: '300px' }}>
            <button className={'btn btn-unstyled text-truncate'} onClick={openModal}>
              <p className={'h3'}>{selectedTask.title}</p>
              <i>{selectedTask.description}</i>
            </button>
          </div>
        </div>
        <div slot="center">
          <div>
            <span className="text-secondary">ID:</span>
            <strong className="ml-2">{selectedTask.id}</strong>
          </div>
          <div className="d-flex flex-row align-items-center">
            <label
              htmlFor={'erc'}
              className={'text-secondary pt-1'}
              style={{ fontWeight: 'normal' }}
            >
              ERC:
            </label>
            <input
              id={'externalReferenceCode'}
              name={'externalReferenceCode'}
              className={'form-control form-control-inline px-2 py-0 ml-2'}
              type={'text'}
              value={selectedTask.externalReferenceCode}
              onChange={handleInputOnChange}
              onBlur={() => {
                handleBasicInfoChanges();
              }}
              onKeyDown={(e) => {
                if (e.code === 'Enter') {
                  handleBasicInfoChanges();
                }
              }}
            />
          </div>
        </div>
        <div slot="right">
          <div className="form-group-autofit mb-0 mt-3">
            <div className="form-group-item">
              <label className="toggle-switch">
                <span className="toggle-switch-label">
                  {selectedTask.enabled ? 'Enabled' : 'Disabled'}
                </span>
                <span className="toggle-switch-check-bar">
                  <input
                    id="enabled"
                    name="enabled"
                    className="toggle-switch-check"
                    role="switch"
                    type="checkbox"
                    value={selectedTask.enabled}
                    defaultChecked={selectedTask.enabled}
                    onChange={() => {
                      console.log(!selectedTask.enabled);
                      const updatedTask = {
                        ...selectedTask,
                        enabled: !selectedTask.enabled,
                      };
                      setSelectedTask(updatedTask);
                      updateTask(updatedTask);
                    }}
                  />
                  <span aria-hidden="true" className="toggle-switch-bar">
                    <span className="toggle-switch-handle"></span>
                  </span>
                </span>
              </label>
            </div>
            <div className="form-group-item">
              <button
                className={'btn btn-sm btn-secondary'}
                onClick={(e) => {
                  e.preventDefault();
                  setIsChatPreviewOpen(!isChatPreviewOpen);
                }}
              >
                {(isChatPreviewOpen ? 'Close' : 'Open') + ' Chat Preview'}
              </button>
            </div>
          </div>
        </div>
      </NavigationBar>
      <div className={'sheet sheet-xl mt-6 px-0'}>
        <Tabs className={'justify-content-center'}>
          <Tab id={'flowEditor'} label={'Flow Editor'} className={'px-0 py-0'}>
            <div>
              <DnDProvider>
                <AITaskFlowEditor />
              </DnDProvider>
            </div>
          </Tab>
          <Tab id={'jsonEditor'} label={'JSON Editor'} className={'container'}>
            <div>
              <JsonEditor
                data={selectedTask.configuration}
                rootName={'configuration'}
                indent={2}
                collapse={2}
                collapseAnimationTime={150}
                maxWidth={'100%'}
                onEdit={handleConfigurationChange}
                onAdd={handleConfigurationChange}
                onDelete={handleConfigurationChange}
              />
            </div>
          </Tab>
        </Tabs>
      </div>
      <AITaskChatPreview isOpen={isChatPreviewOpen} setIsOpen={setIsChatPreviewOpen} />
    </>
  );
};

export default AITaskEdit;
