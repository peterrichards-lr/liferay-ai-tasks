/**
 * @author Louis-Guillaume Durand
 */
import React from 'react';
import { createRoot } from 'react-dom/client';
import { HashRouter, Route, Routes } from 'react-router-dom';

import { ReactFlowProvider } from '@xyflow/react';

import AITaskAdd from './components/AITaskAdd';
import AITaskEdit from './components/AITaskEdit';
import AITaskList from './components/AITaskList';
import {
  ROUTE_TASK_ADD,
  ROUTE_TASK_EDIT,
  ROUTE_TASK_LIST,
} from './constants/AITasksRoutesConstants';
import { AITasksProvider } from './contexts/AITasksContext';
import { ModalProvider } from './contexts/ModalContext';
import { NodeMenuProvider } from './contexts/NodeMenuContext';
import { TooltipProvider } from './contexts/TooltipContext';
import './styles/index.scss';

const AITaskRouting = () => (
  <Routes>
    <Route path={ROUTE_TASK_LIST} element={<AITaskList />} index />
    <Route path={ROUTE_TASK_ADD} element={<AITaskAdd />} />
    <Route path={ROUTE_TASK_EDIT + '/:id'} element={<AITaskEdit />} />
  </Routes>
);

const App = () => {
  return (
    <HashRouter>
      <AITasksProvider>
        <ModalProvider>
          <TooltipProvider>
            <NodeMenuProvider>
              <ReactFlowProvider>
                <AITaskRouting />
              </ReactFlowProvider>
            </NodeMenuProvider>
          </TooltipProvider>
        </ModalProvider>
      </AITasksProvider>
    </HashRouter>
  );
};

class WebComponent extends HTMLElement {
  _isInitialized = false;

  connectedCallback() {
    if (this._isInitialized) {
      return;
    }
    this._isInitialized = true;
    const root = createRoot(this);
    root.render(<App />);
  }
}

const ELEMENT_ID = 'ai-tasks-admin-web';

if (!customElements.get(ELEMENT_ID)) {
  customElements.define(ELEMENT_ID, WebComponent);
}
