package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dao.UserTaskDAO;
import com.stefanini.taskmanager.dto.Task;
import com.stefanini.taskmanager.dto.User;

public class UserTaskServiceImpl implements UserTaskService {
  private static UserTaskServiceImpl userTaskService;
  private UserDAO userDAO;
  private TaskDAO taskDAO;
  private UserTaskDAO userTaskDAO;


  private UserTaskServiceImpl(UserDAO userDAO, TaskDAO taskDAO, UserTaskDAO userTaskDAO) {
    this.userDAO = userDAO;
    this.taskDAO = taskDAO;
    this.userTaskDAO = userTaskDAO;
  }

  public static UserTaskServiceImpl getUserTaskService(
      UserDAO userDAO, TaskDAO taskDAO, UserTaskDAO userTaskDAO) {
    if (userTaskService == null) {
      userTaskService = new UserTaskServiceImpl(userDAO, taskDAO, userTaskDAO);
    }
    return userTaskService;
  }

  @Override
  public void addTaskToUser(String userName, String title) {
    // TODO:rename
    User user = userDAO.findUserByUserName(userName);
    Task task = taskDAO.findTaskByTitle(title);
    userTaskDAO.addTaskToUser(user, task);
  }
}
