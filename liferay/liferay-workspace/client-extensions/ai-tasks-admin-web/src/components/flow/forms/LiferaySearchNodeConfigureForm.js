/**
 * @author Louis-Guillaume Durand
 * @author Petteri Karttunen
 */
import React from 'react';

import { Tab, Tabs } from '../../ui/Tabs';

const LiferaySearchNodeConfigureForm = ({ nodeParameters, onChange }) => {
  return (
    <Tabs>
      <Tab id={'generalSettings'} label={'General Settings'}>
        <div>
          <div className="form-row">
            <div className="form-group col-6">
              <label htmlFor="sxpBlueprintExternalReferenceCode">
                Search Blueprint External Reference Code
              </label>
              <input
                type="text"
                className="form-control"
                id="sxpBlueprintExternalReferenceCode"
                placeholder="Enter an external reference code"
                value={nodeParameters.sxpBlueprintExternalReferenceCode}
                onChange={(e) => {
                  onChange('sxpBlueprintExternalReferenceCode', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted">
                The external reference of a blueprint performing a semantic search.
              </small>
            </div>

            <div className="form-group col-6">
              <label htmlFor="documentResultField">Document Result Field</label>
              <input
                type="text"
                className="form-control"
                id="documentResultField"
                placeholder="Enter location"
                value={nodeParameters.documentResultField}
                onChange={(e) => {
                  onChange('documentResultField', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted">
                The Elasticsearch document field to use for extracting content from search results
                for grounding the LLM within a RAG pipeline.
              </small>
            </div>
          </div>
        </div>
      </Tab>
      <Tab id={'aiBehaviour'} label={'AI Behaviour'}>
        <div>
          <div className="form-group">
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
            <small className="form-text text-muted">
              Limits the number of potential tokens the model considers. Higher values are less
              random.
            </small>
          </div>
        </div>
      </Tab>
      <Tab id={'inputOutput'} label={'Input & Output'}>
        <div>
          <div className="form-row">
            <div className="form-group col-6">
              <label htmlFor="inputParameterName">Input Parameter Name</label>
              <input
                type="text"
                className="form-control"
                id="inputParameterName"
                placeholder="text"
                defaultValue="text"
                value={nodeParameters.inputParameterName}
                onChange={(e) => {
                  onChange('inputParameterName', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted">
                The name of the parameter that hold the user's query.
              </small>
            </div>

            <div className="form-group col-6">
              <label htmlFor="taskContextOutputParameterName">Output Parameter Name</label>
              <input
                type="text"
                className="form-control"
                id="taskContextOutputParameterName"
                placeholder="ragDocuments"
                value={nodeParameters.taskContextOutputParameterName}
                onChange={(e) => {
                  onChange('taskContextOutputParameterName', e.currentTarget.value);
                }}
              />
              <small className="form-text text-muted">
                {
                  'The output parameter name injected in {{taskContext}} as a field. For example, {{taskContext.myOutput}}'
                }
              </small>
            </div>
          </div>
        </div>
      </Tab>
    </Tabs>
  );
};

export default LiferaySearchNodeConfigureForm;
