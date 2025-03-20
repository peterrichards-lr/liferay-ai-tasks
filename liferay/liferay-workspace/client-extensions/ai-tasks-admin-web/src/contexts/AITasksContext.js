/**
 * @author Louis-Guillaume Durand
 */
import React, { createContext, useContext, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import Toast from '../components/ui/Toast';
import { ROUTE_TASK_EDIT } from '../constants/AITasksRoutesConstants';
import LiferayService from '../services/LiferayService';

const AITasksContext = createContext({
  tasks: [],
  setTasks: (tasks) => {},
  selectedTask: null,
  setSelectedTask: (task) => {},
  selectedNode: null,
  setSelectedNode: (task) => {},
  fetchTask: (taskId) => {},
  fetchTasks: () => {},
  addTask: (task) => {},
  duplicateTask: (task) => {},
  importTask: (task) => {},
  exportTask: (taskId) => {},
  updateTask: (updatedTask) => {},
  deleteTask: (task) => {},
  executeTask: (externalReferenceCode, userInput) => {},
  taskExecuting: false,
  loading: false,
  error: null,
  handleFlowConfigurationChange: (newConfig) => {},
  updateGraph: (newNodes, newEdges) => {},
});

const useAITasksContext = () => useContext(AITasksContext);

const AITasksProvider = ({ children }) => {
  const [tasks, setTasks] = useState([]);
  const [selectedTask, setSelectedTask] = useState(null);
  const [selectedNode, setSelectedNode] = useState(null);
  const [loading, setLoading] = useState(true);
  const [taskExecuting, setTaskExecuting] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const successUpdateTimeoutRef = useRef(null);

  const fetchTasks = async () => {
    try {
      const data = await LiferayService.get(
        `/o/ai-tasks/v1.0/ai-tasks?fields=id,title,externalReferenceCode,version,enabled`,
      );
      return data.items;
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  const fetchTask = async (taskId) => {
    try {
      return await LiferayService.get(`/o/ai-tasks/v1.0/ai-tasks/${taskId}`);
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  const addTask = async (task) => {
    try {
      const addedTask = await LiferayService.post(`/o/ai-tasks/v1.0/ai-tasks/`, {
        ...task,
      });
      Toast.open('success', 'Success', `'${addedTask.title}' has been added.`);
      navigate(ROUTE_TASK_EDIT + '/' + addedTask.id);
    } catch (error) {
      console.error('Error adding task:', error.message);
      Toast.open('danger', '', error.message);
    }
  };

  const duplicateTask = async (taskId) => {
    try {
      const addedTask = await LiferayService.post(`/o/ai-tasks/v1.0/ai-tasks/${taskId}/copy`);
      Toast.open('success', 'Success', `'${addedTask.title}' has been added.`);
      const tasks = await fetchTasks();
      setTasks(tasks);
      setSelectedTask(null);
    } catch (error) {
      console.error('Error adding task:', error.message);
      Toast.open('danger', '', error.message);
    }
  };

  const importTask = async (task) => {
    try {
      const importedTask = await LiferayService.post(`/o/ai-tasks/v1.0/ai-tasks/`, {
        ...task,
      });
      Toast.open('success', 'Success', `'${importedTask.title}' has been imported.`);
      setTasks(await fetchTasks());
    } catch (error) {
      console.error('Error importing task:', error.message);
      Toast.open('danger', '', error.message);
    }
  };

  const exportTask = async (taskId) => {
    try {
      return await LiferayService.get(`/o/ai-tasks/v1.0/ai-tasks/${taskId}/export`);
    } catch (error) {
      setError(error.message);
    }
  };

  const updateTask = async (updatedTask) => {
    try {
      const response = await LiferayService.put(`/o/ai-tasks/v1.0/ai-tasks/${selectedTask.id}`, {
        ...updatedTask,
      });
      setSelectedTask(response);
      if (successUpdateTimeoutRef.current != null) {
        clearTimeout(successUpdateTimeoutRef.current);
      }
      successUpdateTimeoutRef.current = setTimeout(() => {
        Toast.open('success', 'Success', `${response.title} has been updated.`);
      }, 1000);
    } catch (error) {
      console.error('Error saving configuration:', error);
      Toast.open('danger', 'Error', error);
    }
  };

  const deleteTask = async (task) => {
    const shouldRemove = confirm(`Are you sure you want to delete '${task.title}'?`);
    if (shouldRemove) {
      try {
        await LiferayService.delete(`/o/ai-tasks/v1.0/ai-tasks/${task.id}`);
        Toast.open('success', 'Success', `'${task.title}' has been deleted.`);
        const tasks = await fetchTasks();
        setTasks(tasks);
        setSelectedTask(null);
      } catch (error) {
        setError(error.message);
        Toast.open('danger', 'Error', error);
      } finally {
        await fetchTasks();
      }
    }
  };

  const executeTask = async (externalReferenceCode, userInput) => {
    try {
      setTaskExecuting(true);
      return await LiferayService.post(`/o/ai-tasks/v1.0/generate/${externalReferenceCode}`, {
        input: {
          text: userInput,
        },
      });
    } catch (error) {
      setError(error.message);
    } finally {
      setTaskExecuting(false);
    }
  };

  const handleFlowConfigurationChange = async (newConfig) => {
    const updatedTask = {
      ...selectedTask,
      configuration: { ...newConfig },
    };
    await updateTask(updatedTask);
  };

  const updateGraph = async (newNodes, newEdges) => {
    const filteredNodes = newNodes.filter((newNode) => newNode.id !== 'entryPoint');
    const updatedNodes = filteredNodes.map((newNode) => {
      return {
        id: newNode.id,
        label: newNode.label || newNode.data.label,
        type: newNode.type,
        condition: newNode.condition || null,
        parameters: newNode.parameters || newNode.data.parameters,
        uiConfiguration: newNode.uiConfiguration || newNode.data.uiConfiguration,
      };
    });
    const updatedEdges = newEdges
      .filter((newEdge) => newEdge.id !== 'entryPointEdge')
      .map((edge) => {
        return {
          id: edge.id,
          source: edge.source,
          target: edge.target,
        };
      });

    const updatedNodesIds = updatedNodes.map((updatedNode) => {
      return updatedNode.id;
    });

    let startNodeId = '';

    if (updatedNodesIds.includes(selectedTask.configuration.startNodeId)) {
      startNodeId = selectedTask.configuration.startNodeId;
    }

    await handleFlowConfigurationChange({
      ...selectedTask.configuration,
      nodes: updatedNodes,
      edges: updatedEdges,
      startNodeId,
    });
  };

  return (
    <AITasksContext.Provider
      value={{
        tasks,
        setTasks,
        selectedTask,
        setSelectedTask,
        selectedNode,
        setSelectedNode,
        fetchTask,
        fetchTasks,
        addTask,
        duplicateTask,
        importTask,
        exportTask,
        updateTask,
        deleteTask,
        executeTask,
        taskExecuting,
        loading,
        error,
        handleFlowConfigurationChange,
        updateGraph,
      }}
    >
      {children}
    </AITasksContext.Provider>
  );
};

export { AITasksContext, AITasksProvider, useAITasksContext };
