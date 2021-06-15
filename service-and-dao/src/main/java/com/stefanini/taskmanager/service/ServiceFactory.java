package com.stefanini.taskmanager.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.config.ApplicationProperties;
import com.stefanini.taskmanager.dao.DAOTaskFactory;
import com.stefanini.taskmanager.dao.DAOUserFactory;
import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.dao.UserDAO;

public class ServiceFactory {
  private static final Logger logger = LogManager.getLogger(ServiceFactory.class);
  private static final ApplicationProperties prop =
      ApplicationProperties.getApplicationProperties();

  public static UserService getUserService() {
    logger.debug("UserService retrieval");
    // TODO:rename in DAOType
    UserDAO userDao = DAOUserFactory.getDAO(prop.getServiceType());
    UserService userService = UserServiceImpl.getUserService(userDao);
    return userService;
  }

  public static TaskService getTaskService() {
    logger.debug("TaskService retrieval");
    TaskDAO taskDao = DAOTaskFactory.getDAO(prop.getServiceType());
    TaskService taskService = TaskServiceImpl.getTaskService(taskDao);
    return taskService;
  }
}
