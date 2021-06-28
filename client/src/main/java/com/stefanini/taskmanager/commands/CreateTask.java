package com.stefanini.taskmanager.commands;

import com.stefanini.taskmanager.Operations;
import com.stefanini.taskmanager.dto.Task;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;

public class CreateTask implements Operations {
  private TaskService taskService = ServiceFactory.getTaskService();
  private Task task;

  public CreateTask(Task task) {
    this.task = task;
  }

  @Override
  public void execute() {
    taskService.createTask(task);
    
  }
}
