package com.stefanini.taskmanager.dao.usertask;

import com.stefanini.taskmanager.dao.UserTaskDAO;
import com.stefanini.taskmanager.dto.Task;
import com.stefanini.taskmanager.dto.User;

public class FileUserTaskDAO implements UserTaskDAO {
  private static FileUserTaskDAO userTaskDAO;

  private FileUserTaskDAO() {}

  public static FileUserTaskDAO getInstanceDAO() {
    if (userTaskDAO == null) {
      userTaskDAO = new FileUserTaskDAO();
    }
    return userTaskDAO;
  }

  @Override
  public boolean addTaskToUser(User user, Task task) {
    return false;
  }
}
