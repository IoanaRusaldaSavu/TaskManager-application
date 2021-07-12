package com.stefanini.taskmanager.commands;

import java.util.List;

import com.stefanini.taskmanager.Operations;
import com.stefanini.taskmanager.dto.Task;
import com.stefanini.taskmanager.dto.User;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.UserTaskService;

public class CreateUserAndAddTasks implements Operations {
  private UserTaskService userTaskService = ServiceFactory.getUserTaskService();
  private User user;
  private List<Task> tasks;

  public CreateUserAndAddTasks(User user, List<Task> tasks) {
    this.user = user;
    this.tasks = tasks;
  }

  @Override
  public void execute() {
    userTaskService.createUserAndAddTasks(user, tasks);
}


  @Override
  public String getName() { // TODO Auto-generated method stub
    return null;
  }
}
