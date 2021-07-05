package com.stefanini.taskmanager.commands;

import java.util.stream.Stream;

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
    Stream<Task> tasks = taskService.getTasks();
    if (tasks != null) {
      tasks.forEach(e -> System.out.println(e));
    } else {
      logger.error("Error or no task added");
    }
  }
}
