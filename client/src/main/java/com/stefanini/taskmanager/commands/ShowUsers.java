package com.stefanini.taskmanager.commands;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.Operations;
import com.stefanini.taskmanager.dto.User;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;

public class ShowUsers implements Operations {
  private static final Logger logger = LogManager.getLogger(ShowUsers.class);
  private UserService userService = ServiceFactory.getUserService();
  

  @Override
  public void execute() {
    List<User> users = userService.getUsers();
    if (users != null) {
      for (User u : users) {
        System.out.println(u);
      }
    } else {
      logger.error("Error or no users added");
    }
  }
}
