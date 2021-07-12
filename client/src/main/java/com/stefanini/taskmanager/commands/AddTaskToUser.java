package com.stefanini.taskmanager.commands;

import com.stefanini.taskmanager.Operations;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.UserTaskService;

public class AddTaskToUser implements Operations {
  private UserTaskService userTaskService = ServiceFactory.getUserTaskService();
  private String userName;
  private String title;

  private static final String name = "addTaskToUser";

  public AddTaskToUser(String userName, String title) {
    this.userName = userName;
    this.title = title;
  }
  
  @Override
  public void execute() {
    userTaskService.addTaskToUser(userName, title);
  }



  public String getName() {
    return name;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
