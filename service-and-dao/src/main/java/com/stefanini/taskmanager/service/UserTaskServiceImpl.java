package com.stefanini.taskmanager.service;

import java.util.List;

import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dao.UserTaskDAO;
import com.stefanini.taskmanager.dto.Task;
import com.stefanini.taskmanager.dto.User;
import com.stefanini.taskmanager.dto.UserTask;

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
  public UserTask addTaskToUser(String userName, String title) {
    User user = userDAO.findUserByUserName(userName);
    Task task = taskDAO.findTaskByTitle(title);
    if (user == null || task == null) {
      // TODO:log
      return null;
    }
    if (!userTaskDAO.addTaskToUser(user, task)) {
      return null;
    }
    return new UserTask(user.getUserName(), task.getTaskTitle(), task.getDescription());
  }
  // TODO:find better place for this method
  public void createUserAndAddTasks(User user, List<Task> tasks) {
    userDAO.createUser(user);
    user = userDAO.findUserByUserName(user.getUserName());
    Task task;
    for (Task t : tasks) {
      taskDAO.createTask(t);
      task = taskDAO.findTaskByTitle(t.getTaskTitle());
      userTaskDAO.addTaskToUser(user, task);
    }
  }
}
