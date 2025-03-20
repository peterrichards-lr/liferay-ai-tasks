/**
 * @author Petteri Karttunen
 */
import React from 'react';

import { Tab, Tabs } from '../../ui/Tabs';

const OpenAIImageModelNodeConfigureForm = ({ nodeParameters, onChange }) => {
  return (
    <Tabs>
      <Tab id={'generalSettings'} label={'General Settings'}>
        <div>
          <div className="form-group">
            <label htmlFor="apiKey">API Key</label>
            <input
              type="text"
              className="form-control"
              id="apiKey"
              placeholder="Enter API key"
              value={nodeParameters.apiKey}
              onChange={(e) => {
                onChange('apiKey', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted">
              OpenAI API key. To use environment variables, prefix the variable name with env:
            </small>
          </div>
          <div className="form-row">
            <div className="form-group col-6">
              <label htmlFor="modelName">Model Name</label>
              <input
                type="text"
                className="form-control"
                id="modelName"
                placeholder="Enter the model name"
                value={nodeParameters.modelName}
                onChange={(e) => {
                  onChange('modelName', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted">Enter the OpenAI model name</small>
            </div>
            <div className="form-group col-6">
              <label htmlFor="responseFormat">Response format</label>
              <select
                className="form-control"
                id="responseFormat"
                value={nodeParameters.responseFormat}
                onChange={(e) => {
                  onChange('responseFormat', e.currentTarget.value);
                }}
              >
                <option value={'b64_json'}>Base64 encoded JSON</option>
                <option value={'url'}>URL</option>
              </select>
              <small className="form-text text-muted">The response format.</small>
            </div>
          </div>
        </div>
      </Tab>
      <Tab id={'imageSettings'} label={'Image Settings'}>
        <div>
          <div className="form-group">
            <label htmlFor="quality">Quality</label>
            <select
              className="form-control"
              id="quality"
              value={nodeParameters.quality}
              onChange={(e) => {
                onChange('quality', e.currentTarget.value);
              }}
            >
              <option value={'standard'}>Standard</option>
              <option value={'hd'}>HD</option>
            </select>
            <small className="form-text text-muted">
              See https://platform.openai.com/docs/api-reference/images/create
            </small>
          </div>
          <div className="form-group">
            <label htmlFor="style">Style</label>
            <select
              className="form-control"
              id="style"
              value={nodeParameters.style}
              onChange={(e) => {
                onChange('style', e.currentTarget.value);
              }}
            >
              <option value={'natural'}>Natural</option>
              <option value={'vivid'}>Vivid</option>
            </select>
            <small className="form-text text-muted">
              See https://platform.openai.com/docs/api-reference/images/create
            </small>
          </div>
          <div className="form-group">
            <label htmlFor="size">Size</label>
            <input
              type="text"
              className="form-control"
              id="size"
              placeholder="Enter size"
              value={nodeParameters.size}
              onChange={(e) => {
                onChange('size', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted">
              See https://platform.openai.com/docs/api-reference/images/create
            </small>
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
          <div className="form-row">
            <div className="form-group col-6">
              <label htmlFor="user">User</label>
              <input
                type="text"
                className="form-control"
                id="user"
                placeholder="Enter user"
                value={nodeParameters.user}
                onChange={(e) => {
                  onChange('user', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted">
                See https://platform.openai.com/docs/api-reference/images/create
              </small>
            </div>
            <div className="form-group col-6">
              <label htmlFor="maxRetries">Max Retries</label>
              <input
                type="number"
                className="form-control"
                id="maxRetries"
                min="0"
                value={nodeParameters.maxRetries}
                onChange={(e) => {
                  onChange('maxRetries', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted">
                The maximum number of retries in case of API call failure.
              </small>
            </div>
          </div>
          <div className="form-group">
            <div className="custom-control custom-checkbox custom-control-outside">
              <label>
                <input
                  className="custom-control-input"
                  defaultChecked={nodeParameters.logRequests}
                  id="logRequests"
                  name="logRequests"
                  type="checkbox"
                  value={nodeParameters.logRequests}
                  onChange={(e) => {
                    onChange('logRequests', e.currentTarget.checked);
                  }}
                />
                <span className="custom-control-label">Log Requests in the console.</span>
              </label>
            </div>
            <div className="custom-control custom-checkbox custom-control-outside">
              <label>
                <input
                  className="custom-control-input"
                  defaultChecked={nodeParameters.logResponses}
                  id="logResponses"
                  name="logResponses"
                  type="checkbox"
                  value={nodeParameters.logResponses}
                  onChange={(e) => {
                    onChange('logResponses', e.currentTarget.checked);
                  }}
                />
                <span className="custom-control-label">Log Responses in the console.</span>
              </label>
            </div>
          </div>
        </div>
      </Tab>
    </Tabs>
  );
};

export default OpenAIImageModelNodeConfigureForm;
