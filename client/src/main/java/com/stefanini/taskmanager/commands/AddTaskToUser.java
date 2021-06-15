package com.stefanini.taskmanager.commands;

import com.stefanini.taskmanager.Operations;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;

public class AddTaskToUser implements Operations {
  private UserService userService = ServiceFactory.getUserService();
  String userName;
  String title;

  public AddTaskToUser(String userName, String title) {
    this.userName = userName;
    this.title = title;
  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    userService.addTaskToUser(userName, title);
  }
}
