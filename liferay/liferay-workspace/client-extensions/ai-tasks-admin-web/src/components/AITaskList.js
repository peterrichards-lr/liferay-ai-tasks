/**
 * @author Louis-Guillaume Durand
 */
import React, { useEffect } from 'react';
import { NavLink } from 'react-router-dom';

import { ROUTE_TASK_ADD, ROUTE_TASK_EDIT } from '../constants/AITasksRoutesConstants';
import { useAITasksContext } from '../contexts/AITasksContext';
import { getFormattedUTC } from '../utils/dateUtils';
import Alert from './ui/Alert';
import EmptyState from './ui/EmptyState';
import Icon from './ui/Icon';
import Label from './ui/Label';
import LoadingIndicator from './ui/LoadingIndicator';
import NavigationBar from './ui/NavigationBar';
import Toast from './ui/Toast';

const AITaskList = () => {
  const {
    tasks,
    fetchTasks,
    setTasks,
    duplicateTask,
    importTask,
    exportTask,
    deleteTask,
    setSelectedTask,
    loading,
    error,
  } = useAITasksContext();

  const handleExportTask = async (taskId) => {
    const task = await exportTask(taskId);
    if (task) {
      const jsonString = JSON.stringify(task, null, 2);
      const blob = new Blob([jsonString], { type: 'application/json' });
      const url = URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.download = `AI_Task_${taskId}_${getFormattedUTC(new Date())}.json`;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
      URL.revokeObjectURL(url);
    }
  };
  const handleFileChange = (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = async (event) => {
      const jsonData = JSON.parse(event.target.result);
      importTask(jsonData);
    };

    reader.onerror = () => {
      Toast.open('danger', '', 'Error reading the file.');
    };

    reader.readAsText(file);
  };

  useEffect(() => {
    setSelectedTask(null);
    fetchTasks().then((tasks) => {
      setTasks(tasks);
    });
  }, []);

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

  return (
    <>
      <NavigationBar>
        <li slot="right" className={'nav-item'}>
          <input
            id={'uploadAITask'}
            type={'file'}
            accept=".json"
            hidden
            onChange={handleFileChange}
          />
          <button
            className={'btn btn-sm btn-secondary'}
            onClick={(e) => {
              e.preventDefault();
              document.querySelector('#uploadAITask').click();
            }}
          >
            <Icon name={'upload'} /> Import File
          </button>
        </li>
        <li slot="right" className={'nav-item'}>
          <NavLink to={ROUTE_TASK_ADD}>
            <button
              className={
                'btn btn-primary dropdown-toggle nav-btn nav-btn-monospaced navbar-breakpoint-down-d-none'
              }
            >
              <Icon name={'plus'} />
            </button>
          </NavLink>
        </li>
      </NavigationBar>
      <div className={'container container-fluid-max-xl container-view mt-6'}>
        {tasks === undefined || tasks.length === 0 ? (
          <EmptyState
            image={'empty'}
            button={
              <NavLink to={ROUTE_TASK_ADD}>
                <button className={'btn btn-secondary'}>New AI Task</button>
              </NavLink>
            }
          />
        ) : (
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Title</th>
                <th>ID</th>
                <th>Endpoint</th>
                <th>Status</th>
                <th>Version</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {tasks.map((task) => (
                <tr key={task.id}>
                  <td style={{ maxWidth: '250px' }}>
                    <div className="table-title">
                      <NavLink to={ROUTE_TASK_EDIT + '/' + task.id}>{task.title}</NavLink>
                    </div>
                  </td>
                  <td>{task.id}</td>
                  <td>
                    <code>{'/o/ai-tasks/v1.0/generate/' + task.externalReferenceCode}</code>
                  </td>
                  <td>
                    {task.enabled ? (
                      <Label type={'success'}>Enabled</Label>
                    ) : (
                      <Label type={'warning'}>Disabled</Label>
                    )}
                  </td>
                  <td>{task.version}</td>
                  <td>
                    <button
                      className={'btn btn-monospaced btn-sm'}
                      onClick={(e) => {
                        e.preventDefault();
                        handleExportTask(task.id);
                      }}
                    >
                      <Icon name={'download'} />
                    </button>
                    <button
                      className={'btn btn-monospaced btn-sm'}
                      onClick={(e) => {
                        e.preventDefault();
                        duplicateTask(task.id);
                      }}
                    >
                      <Icon name={'copy'} />
                    </button>
                    <button
                      className={'btn btn-monospaced btn-sm'}
                      onClick={(e) => {
                        e.preventDefault();
                        deleteTask(task);
                      }}
                    >
                      <Icon name={'trash'} style={{ color: 'red' }} />
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </>
  );
};

export default AITaskList;
