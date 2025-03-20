/**
 * @author Petteri Karttunen
 */
import React from 'react';

import { Tab, Tabs } from '../../ui/Tabs';

const GoogleImagenNodeConfigureForm = ({ nodeParameters, onChange }) => {
  return (
    <Tabs>
      <Tab id={'generalSettings'} label={'General Settings'}>
        <div>
          <div className="form-group">
            <label htmlFor="endpoint">Endpoint</label>
            <input
              type="text"
              className="form-control"
              id="endpoint"
              placeholder="Enter endpoint"
              value={nodeParameters.endpoint}
              onChange={(e) => {
                onChange('endpoint', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted"></small>
          </div>
          <div className="form-row">
            <div className="form-group col-6">
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
            <div className="form-group col-6">
              <label htmlFor="publisher">Publisher</label>
              <input
                type="text"
                className="form-control"
                id="publisher"
                placeholder="Enter publisher name"
                value={nodeParameters.publisher}
                onChange={(e) => {
                  onChange('publisher', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted"></small>
            </div>
          </div>
          <div className="form-row">
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
            <div className="form-group  col-6">
              <label htmlFor="modelName">Model</label>
              <input
                type="text"
                className="form-control"
                id="modelName"
                placeholder="Enter model name"
                value={nodeParameters.modelName}
                onChange={(e) => {
                  onChange('modelName', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted">
                See https://cloud.google.com/vertex-ai/generative-ai/docs/image/model-versioning
              </small>
            </div>
          </div>
        </div>
      </Tab>
      <Tab id={'imageSettings'} label={'Image Settings}'}>
        <div>
          <div className="form-row">
            <div className="form-group col-6">
              <label htmlFor="guidanceScale">Guidance Scale</label>
              <input
                type="text"
                className="form-control"
                id="guidanceScale"
                placeholder="Enter guidance scale (0-100)"
                value={nodeParameters.guidanceScale}
                onChange={(e) => {
                  onChange('guidanceScale', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted"></small>
            </div>
            <div className="form-group col-6">
              <label htmlFor="sampleImageSize">Sample Image Size</label>
              <input
                type="text"
                className="form-control"
                id="sampleImageSize"
                placeholder="Enter sample image size"
                value={nodeParameters.sampleImageSize}
                onChange={(e) => {
                  onChange('sampleImageSize', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted"></small>
            </div>
          </div>
          <div className="form-row">
            <div className="form-group col-6">
              <label htmlFor="seed">Seed</label>
              <input
                type="text"
                className="form-control"
                id="seed"
                placeholder="Enter seed"
                value={nodeParameters.seed}
                onChange={(e) => {
                  onChange('seed', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted">
                Use a particular seed to always generate the same image with the same seed.
              </small>
            </div>
            <div className="form-group col-6">
              <label htmlFor="sampleImageStyle">Sample Image Style</label>
              <select
                className="form-control"
                id="sampleImageStyle"
                value={nodeParameters.sampleImageStyle}
                onChange={(e) => {
                  onChange('sampleImageStyle', e.currentTarget.value);
                }}
              >
                <option value={'cyberpunk'}>Cyberpunk</option>
                <option value={'digitalArt'}>Digital Art</option>
                <option value={'landscape'}>Landscape</option>
                <option value={'photograph'}>Photograph</option>
                <option value={'popArt'}>Pop Art</option>
                <option value={'sketch'}>Sketch</option>
                <option value={'watercolor'}>Watercolor</option>
              </select>
            </div>
          </div>
        </div>
      </Tab>
      <Tab id={'promptSettings'} label={'Prompt Settings'}>
        <div>
          <div className="form-group">
            <label htmlFor="language">Prompt Language</label>
            <input
              type="text"
              className="form-control"
              id="language"
              placeholder="Enter language"
              value={nodeParameters.guidanceScale}
              onChange={(e) => {
                onChange('guidanceScale', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted">
              Enter 2 character language code. See supported languages at
              https://cloud.google.com/vertex-ai/generative-ai/docs/image/model-versioning.
            </small>
          </div>
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
            <label htmlFor="negativePrompt">Negative Prompt</label>
            <textarea
              className="form-control"
              id="negativePrompt"
              placeholder="Enter negative prompt"
              rows="3"
              value={nodeParameters.negativePrompt}
              onChange={(e) => {
                onChange('negativePrompt', e.currentTarget.value);
              }}
            />
            <small className="form-text text-muted">
              Define things you don't want to see in the picture.
            </small>
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

export default GoogleImagenNodeConfigureForm;
