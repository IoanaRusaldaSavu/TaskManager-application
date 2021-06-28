package com.stefanini.taskmanager.commands;

import com.stefanini.taskmanager.Operations;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.UserTaskService;

public class AddTaskToUser implements Operations {
  private UserTaskService userTaskService = ServiceFactory.getUserTaskService();
  private String userName;
  private String title;

  public AddTaskToUser(String userName, String title) {
    this.userName = userName;
    this.title = title;
  }
  
  @Override
  public void execute() {
    if (userTaskService == null) System.out.println("ceva");
    userTaskService.addTaskToUser(userName, title);
  }
  
}
