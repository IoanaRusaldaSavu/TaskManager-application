package com.stefanini.taskmanager.commands;

import com.stefanini.taskmanager.Operations;
import com.stefanini.taskmanager.dto.User;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;

public class CreateUser implements Operations {
  private UserService userService = ServiceFactory.getUserService();
  private User user;

  public CreateUser(User user) {
    this.user = user;
  }

  @Override
  public void execute() {
    userService.createUser(user);
  }
}
