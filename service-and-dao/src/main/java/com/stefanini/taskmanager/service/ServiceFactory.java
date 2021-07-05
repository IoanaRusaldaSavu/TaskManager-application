package com.stefanini.taskmanager.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.config.ApplicationProperties;
import com.stefanini.taskmanager.dao.DAOTaskFactory;
import com.stefanini.taskmanager.dao.DAOUserFactory;
import com.stefanini.taskmanager.dao.DAOUserTaskFactory;
import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dao.UserTaskDAO;
import com.stefanini.taskmanager.service.proxy.EmailProxy;
import com.stefanini.taskmanager.service.proxy.TransactionProxy;

public class ServiceFactory {
  private static final Logger logger = LogManager.getLogger(ServiceFactory.class);
  private static final ApplicationProperties prop =
      ApplicationProperties.getApplicationProperties();

  public static UserService getUserService() {
    logger.debug("UserService retrieval");
    UserDAO userDao = DAOUserFactory.getDAO(prop.getDaoType());
    UserService userService = UserServiceImpl.getUserService(userDao);
    userService = (UserService) EmailProxy.newInstance(userService);
    userService = (UserService) TransactionProxy.newInstance(userService);
    return userService;
  }

  public static TaskService getTaskService() {
    logger.debug("TaskService retrieval");
    TaskDAO taskDao = DAOTaskFactory.getDAO(prop.getDaoType());
    TaskService taskService = TaskServiceImpl.getTaskService(taskDao);
    taskService = (TaskService) TransactionProxy.newInstance(taskService);
    return taskService;
  }

  public static UserTaskService getUserTaskService() {
    logger.debug("UserTaskService retrieval");
    UserDAO userDao = DAOUserFactory.getDAO(prop.getDaoType());
    TaskDAO taskDao = DAOTaskFactory.getDAO(prop.getDaoType());
    UserTaskDAO userTaskDao = DAOUserTaskFactory.getDAO(prop.getDaoType());
    UserTaskService userTaskService =
        UserTaskServiceImpl.getUserTaskService(userDao, taskDao, userTaskDao);
    userTaskService = (UserTaskService) EmailProxy.newInstance(userTaskService);
    userTaskService = (UserTaskService) TransactionProxy.newInstance(userTaskService);
    return userTaskService;
  }
}
