/**
 * @author Petteri Karttunen
 * @author Louis-Guillaume Durand
 */
import React from 'react';

import { JsonEditor } from 'json-edit-react';

import { Tab, Tabs } from '../../ui/Tabs';

const HuggingFaceChatModelNodeConfigureForm = ({ nodeParameters, onChange }) => {
  const rangeWidth = nodeParameters.temperature * 100;

  const handleToolsChange = ({ newData }) => {
    onChange('tools', newData);
  };

  return (
    <Tabs>
      <Tab id={'generalSettings'} label={'General Settings'}>
        <div>
          <div className="form-group">
            <label htmlFor="accessToken">Access Token</label>
            <input
              type="text"
              className="form-control"
              id="accessToken"
              placeholder="Enter access token"
              value={nodeParameters.accessToken}
              onChange={(e) => {
                onChange('accessToken', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted">
              HuggingFace access token. To use environment variables, prefix the variable name with
              env:
            </small>
          </div>
          <div className="form-group">
            <label htmlFor="baseUrl">Base URL</label>
            <input
              type="text"
              className="form-control"
              id="baseUrl"
              placeholder="Enter base URL"
              value={nodeParameters.baseUrl}
              onChange={(e) => {
                onChange('baseUrl', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted"></small>
          </div>
          <div className="form-group">
            <label htmlFor="modelId">Model ID</label>
            <input
              type="text"
              className="form-control"
              id="modelId"
              placeholder="Enter the model ID"
              value={nodeParameters.modelId}
              onChange={(e) => {
                onChange('modelId', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted"></small>
          </div>
          <div className="form-row">
            <div className="form-group col-6">
              <label className="toggle-switch">
                <span className="toggle-switch-label">Use Chat Memory</span>
                <span className="toggle-switch-check-bar">
                  <input
                    id="useChatMemory"
                    className="toggle-switch-check"
                    role="switch"
                    type="checkbox"
                    value={nodeParameters.useChatMemory}
                    onChange={(e) => {
                      onChange('useChatMemory', e.currentTarget.checked);
                    }}
                    defaultChecked={nodeParameters.useChatMemory}
                  />
                  <span aria-hidden="true" className="toggle-switch-bar">
                    <span className="toggle-switch-handle"></span>
                  </span>
                </span>
                <span className="toggle-switch-text">
                  <small className="form-text text-muted">Enable or disable chat memory.</small>
                </span>
              </label>
            </div>
            <div className="form-group col-6">
              <label htmlFor="memoryMaxMessages">Memory Max Messages</label>
              <input
                type="number"
                className="form-control"
                id="memoryMaxMessages"
                min="1"
                value={nodeParameters.memoryMaxMessages}
                onChange={(e) => {
                  onChange('memoryMaxMessages', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted">
                Maximum number of messages to store in chat memory.
              </small>
            </div>
          </div>
        </div>
      </Tab>
      <Tab id={'modelSettings'} label={'Model'}>
        <div>
          <div className="form-group">
            <label htmlFor="temperature">Temperature</label>
            <div className="clay-range" data-toggle="clay-css-range">
              <div className="clay-range-title">
                <span className="clay-range-value">{nodeParameters.temperature}</span>
              </div>
              <div className="clay-range-input" data-label-min="0" data-label-max="1">
                <input
                  id={'temperature'}
                  className={'form-control-range'}
                  type={'range'}
                  min={0}
                  max={1}
                  step={0.1}
                  value={nodeParameters.temperature}
                  onChange={(e) => {
                    onChange('temperature', e.currentTarget.value);
                  }}
                />
                <div className="clay-range-track"></div>
                <div className="clay-range-progress" style={{ width: rangeWidth + '%' }}>
                  <div className="clay-range-thumb"></div>
                </div>
              </div>
            </div>
            <small className="form-text text-muted">
              Controls the randomness of the output (0.0 - 1.0). Higher values result in more random
              output.
            </small>
          </div>
          <div className="form-group">
            <label htmlFor="maxNewTokens">Max New Tokens</label>
            <input
              type="number"
              className="form-control"
              id="maxNewTokens"
              min="1"
              value={nodeParameters.maxNewTokens}
              onChange={(e) => {
                onChange('maxNewTokens', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted">
              See https://huggingface.co/docs/api-inference/tasks/chat-completion
            </small>
          </div>
          <div className="custom-control custom-checkbox custom-control-outside">
            <label>
              <input
                className="custom-control-input"
                defaultChecked={nodeParameters.waitForModel}
                id="waitForModel"
                name="waitForModel"
                type="checkbox"
                value={nodeParameters.logRequests}
                onChange={(e) => {
                  onChange('waitForModel', e.currentTarget.checked);
                }}
              />
              <span className="custom-control-label">Wait for the model</span>
            </label>
          </div>
        </div>
      </Tab>
      <Tab id={'promptSettings'} label={'Prompt Settings'}>
        <div>
          <div className="form-group">
            <label htmlFor="promptTemplate">Prompt Template</label>
            <textarea
              className="form-control"
              id="promptTemplate"
              placeholder="Enter prompt template (e.g. {{input.text}})"
              rows="3"
              value={nodeParameters.promptTemplate || '{{input.text}}'}
              onChange={(e) => {
                onChange('promptTemplate', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted">The prompt template to use.</small>
          </div>
          <div className="form-group">
            <label htmlFor="systemMessage">System Message</label>
            <textarea
              className="form-control"
              id="systemMessage"
              rows="3"
              value={nodeParameters.systemMessage}
              onChange={(e) => {
                onChange('systemMessage', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted">
              Instructions given to the model to set up its behavior.
            </small>
          </div>
        </div>
      </Tab>
      <Tab id={'toolsSettings'} label={'Tools'}>
        <div>
          <div className="form-group">
            <label htmlFor="tools">Tools</label>
            <JsonEditor
              data={nodeParameters.tools || {}}
              indent={2}
              collapse={2}
              collapseAnimationTime={150}
              maxWidth={'100%'}
              onEdit={handleToolsChange}
              onAdd={handleToolsChange}
              onDelete={handleToolsChange}
            />
            <small className="form-text text-muted">Tools configuration as JSON.</small>
          </div>
        </div>
      </Tab>
      <Tab id={'outputSettings'} label={'Output'}>
        <div>
          <div className="form-group">
            <label htmlFor="url">Output parameter name</label>
            <input
              type="text"
              className="form-control"
              id="outputParameterName"
              placeholder="Enter the name"
              value={nodeParameters.outputParameterName}
              onChange={(e) => {
                onChange('outputParameterName', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted">The name of the output parameter.</small>
          </div>
          <div className="form-group">
            <label htmlFor="taskContextOutputParameterName">
              Task context output parameter name
            </label>
            <input
              type="text"
              className="form-control"
              id="taskContextOutputParameterName"
              placeholder="Enter the name"
              value={nodeParameters.taskContextOutputParameterName}
              onChange={(e) => {
                onChange('taskContextOutputParameterName', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted">
              The name of the task context output parameter. Use this if this node is not the final
              output node, but sharing output data with the next nodes in chain.
            </small>
          </div>
        </div>
      </Tab>
      <Tab id={'advancedSettings'} label={'Advanced'}>
        <div>
          <div className="form-group">
            <label htmlFor="timeout">Timeout</label>
            <input
              type="number"
              className="form-control"
              id="timeout"
              min="0"
              value={nodeParameters.timeout}
              onChange={(e) => {
                onChange('timeout', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted">Timeout in seconds.</small>
          </div>
        </div>
      </Tab>
    </Tabs>
  );
};

export default HuggingFaceChatModelNodeConfigureForm;
