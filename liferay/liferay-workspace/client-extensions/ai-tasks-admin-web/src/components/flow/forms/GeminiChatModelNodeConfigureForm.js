/**
 * @author Louis-Guillaume Durand
 * @author Petteri Karttunen
 */
import React from 'react';

import { JsonEditor } from 'json-edit-react';

import { Tab, Tabs } from '../../ui/Tabs';

const GeminiChatModelNodeConfigureForm = ({ nodeParameters, onChange }) => {
  const rangeWidth = nodeParameters.temperature * 100;

  const handleToolsChange = ({ newData }) => {
    onChange('tools', newData);
  };

  return (
    <Tabs>
      <Tab id={'generalSettings'} label={'General Settings'}>
        <div>
          <div className="form-group">
            <label htmlFor="project">Project</label>
            <input
              type="text"
              className="form-control"
              id="project"
              placeholder="Enter project name"
              value={nodeParameters.project}
              onChange={(e) => {
                onChange('project', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted">
              The name of the project this configuration applies to.
            </small>
          </div>
          <div className="form-row">
            <div className="form-group col-6">
              <label htmlFor="model">Model</label>
              {/* TODO: Provide an endpoint to fetch Gemini models */}
              <select
                className="form-control"
                id="model"
                value={nodeParameters.model}
                onChange={(e) => {
                  onChange('model', e.currentTarget.value);
                }}
              >
                <option value={'gemini-2.0-flash-exp'}>
                  Gemini 2.0 Flash (gemini-2.0-flash-exp)
                </option>
                <option value={'gemini-1.5-flash'}>Gemini 1.5 Flash (gemini-1.5-flash)</option>
                <option value={'gemini-1.5-flash-8b'}>
                  Gemini 1.5 Flash-8B (gemini-1.5-flash-8b)
                </option>
                <option value={'gemini-1.5-pro'}>Gemini 1.5 Pro (gemini-1.5-pro)</option>
              </select>
              <small className="form-text text-muted">
                The Gemini model variant name to use:
                https://ai.google.dev/gemini-api/docs/models/gemini
              </small>
            </div>
            <div className="form-group col-6">
              <label htmlFor="location">Location</label>
              {/* TODO: Provide an endpoint to fetch Google Cloud locations for Gemini */}
              <select
                className="form-control"
                id="location"
                value={nodeParameters.location}
                onChange={(e) => {
                  onChange('location', e.currentTarget.value);
                }}
              >
                <option value={'us-central1'}>Iowa (us-central1)</option>
                <option value={'us-west1'}>Oregon (us-west1)</option>
                <option value={'us-west4'}>Las Vegas (us-west4)</option>
                <option value={'us-east4'}>N. Virginia (us-east4)</option>
                <option value={'europe-west1'}>Belgium (europe-west1)</option>
                <option value={'europe-north1'}>Finland (europe-north1)</option>
                <option value={'asia-southeast1'}>Singapore (asia-southeast1)</option>
              </select>
              <small className="form-text text-muted">
                Gemini serving location in Google Cloud:
                https://cloud.google.com/gemini/docs/locations.
              </small>
            </div>
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
          <div className="form-group">
            <div className="custom-control custom-checkbox custom-control-outside">
              <label>
                <input
                  className="custom-control-input"
                  defaultChecked={nodeParameters.useGoogleSearch}
                  id="useGoogleSearch"
                  name="useGoogleSearch"
                  type="checkbox"
                  value={nodeParameters.useGoogleSearch}
                  onChange={(e) => {
                    onChange('useGoogleSearch', e.currentTarget.checked);
                  }}
                />
                <span className="custom-control-label">Ground results with Google Search.</span>
              </label>
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
          <div className="form-row">
            <div className="form-group col-6">
              <label htmlFor="topK">Top K</label>
              <input
                type="number"
                className="form-control"
                id="topK"
                min="1"
                value={nodeParameters.topK}
                onChange={(e) => {
                  onChange('topK', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted"></small>
            </div>
            <div className="form-group col-6">
              <label htmlFor="topP">Top P</label>
              <input
                type="number"
                className="form-control"
                id="topP"
                min="1"
                value={nodeParameters.topP}
                onChange={(e) => {
                  onChange('topP', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted"></small>
            </div>
          </div>
          <div className="form-group">
            <label htmlFor="maxOutputTokens">Max Output Tokens</label>
            <input
              type="number"
              className="form-control"
              id="maxOutputTokens"
              min="1"
              value={nodeParameters.maxOutputTokens}
              onChange={(e) => {
                onChange('maxOutputTokens', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted"></small>
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
            <label htmlFor="responseSchema">Response Schema</label>
            <textarea
              className="form-control"
              id="responseSchema"
              rows="3"
              onChange={(e) => {
                onChange('responseSchema', JSON.parse(e.currentTarget.value));
              }}
              value={JSON.stringify(nodeParameters.responseSchema, undefined, 2)}
            />
            <small className="form-text text-muted">Response schema as JSON.</small>
          </div>
          <div className="form-group">
            <label htmlFor="safetySettings">Safety Settings</label>
            <textarea
              className="form-control"
              id="safetySettings"
              rows="3"
              onChange={(e) => {
                onChange('safetySettings', JSON.parse(e.currentTarget.value));
              }}
              value={JSON.stringify(nodeParameters.safetySettings, undefined, 2)}
            />
            <small className="form-text text-muted"></small>
          </div>
          <div className="form-group">
            <label htmlFor="allowedFunctionNames">Allowed Function Names</label>
            <input
              type="text"
              className="form-control"
              id="allowedFunctionNames"
              value={nodeParameters.allowedFunctionNames}
              onChange={(e) => {
                onChange('allowedFunctionNames', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted">Allowed function names.</small>
          </div>
          <div className="form-group">
            <label htmlFor="vertexSearchDatastore">Vertex Search Data Store</label>
            <input
              type="text"
              className="form-control"
              id="vertexSearchDatastore"
              value={nodeParameters.vertexSearchDatastore}
              onChange={(e) => {
                onChange('vertexSearchDatastore', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted"></small>
          </div>
          <div className="form-group">
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

export default GeminiChatModelNodeConfigureForm;
