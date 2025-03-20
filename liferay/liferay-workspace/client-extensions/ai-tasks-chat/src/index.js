import React from 'react';
import { createRoot } from 'react-dom/client';

import AITasksChat from './components/AITasksChat';

const App = () => <AITasksChat />;

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

const ELEMENT_ID = 'ai-tasks-chat';

if (!customElements.get(ELEMENT_ID)) {
  customElements.define(ELEMENT_ID, WebComponent);
}
