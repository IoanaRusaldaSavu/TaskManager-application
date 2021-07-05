package com.stefanini.taskmanager.service;

import java.util.stream.Stream;

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


  public User createUser(User user) {
    User createdUser = userDAO.createUser(user);
    if (createdUser == null) {
      logger.error("Error or userName alreadyUsed");
    }
    return createdUser;
  }

  public Stream<User> getUsers() {
    return userDAO.getUsers();
  }
}
