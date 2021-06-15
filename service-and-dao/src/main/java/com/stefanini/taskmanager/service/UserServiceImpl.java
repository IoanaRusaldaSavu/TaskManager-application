package com.stefanini.taskmanager.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dto.User;

public class UserServiceImpl implements UserService {
  private UserDAO userDAO;
  private static UserServiceImpl userServiceImpl;
  private Logger logger = LogManager.getLogger(UserServiceImpl.class);

  private UserServiceImpl(UserDAO userDAO) {
    logger.info("UserServiceImpl instantiation");
    this.userDAO = userDAO;
  }

  public static UserServiceImpl getUserService(UserDAO userDAO) {
    if (userServiceImpl == null) {
      return new UserServiceImpl(userDAO);
    }
    return userServiceImpl;
  }

  public void createUser(User user) {
    if (userDAO.createUser(user) == null) {
      logger.error("Error or userName alreadyUsed");
      
    }
  }

  public List<User> getUsers() {
    return userDAO.getUsers();
  }

  public void addTaskToUser(String userName, String title) {
    if (!userDAO.addTask(userName, title)) {
      logger.error("Error or no task given");
    }
  }
}
