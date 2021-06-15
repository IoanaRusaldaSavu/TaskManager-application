package com.stefanini.taskmanager.commands;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.Operations;
import com.stefanini.taskmanager.dto.Task;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;

public class ShowTasks implements Operations {
  private static final Logger logger = LogManager.getLogger(ShowTasks.class);
  private TaskService taskService = ServiceFactory.getTaskService();

  @Override
  public void execute() {
    List<Task> tasks = taskService.getTasks();
    if (tasks != null) {
      for (Task t : tasks) {
        System.out.println(t);
      }
    } else {
      logger.error("Error or no task added");
    }
  }
}
