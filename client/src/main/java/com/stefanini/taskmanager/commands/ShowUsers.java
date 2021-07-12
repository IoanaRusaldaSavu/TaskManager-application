package com.stefanini.taskmanager.commands;

import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.Operations;
import com.stefanini.taskmanager.dto.User;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;

public class ShowUsers implements Operations {
  private static final Logger logger = LogManager.getLogger(ShowUsers.class);
  private UserService userService = ServiceFactory.getUserService();
  private static final String name = "showUsers";

  @Override
  public void execute() {
    Stream<User> users = userService.getUsers();
    if (users != null) {
      users.forEach(e -> System.out.println(e));
    } else {
      logger.error("Error or no users added");
    }
  }

 

  public String getName() {
    return name;
  }
}
