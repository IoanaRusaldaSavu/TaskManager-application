package com.stefanini.taskmanager.service;

import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.dto.Task;

public class TaskServiceImpl implements TaskService {

  private TaskDAO taskDAO;
  private static TaskServiceImpl taskServiceImpl;
  private static Logger logger = LogManager.getLogger(TaskServiceImpl.class);

  private TaskServiceImpl(TaskDAO taskDAO) {
    logger.info("TaskServiceImpl instantiation");
    this.taskDAO = taskDAO;
  }

  public static TaskServiceImpl getTaskService(TaskDAO taskDAO) {
    if (taskServiceImpl == null) {
      return new TaskServiceImpl(taskDAO);
    }
    return taskServiceImpl;
  }

  public void createTask(Task task) {
    if (taskDAO.createTask(task) == null) {
      logger.error("Error or Task already exist");
    }
  }

  public Stream<Task> getTasks() {
    return taskDAO.getTasks();
  }
}
