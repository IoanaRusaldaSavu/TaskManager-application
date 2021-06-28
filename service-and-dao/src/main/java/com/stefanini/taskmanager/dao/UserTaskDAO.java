package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.dto.Task;
import com.stefanini.taskmanager.dto.User;
/**
 * TODO:description
 *
 * @author isavu1
 */
public interface UserTaskDAO {
  /**
   * @param user
   * @param task
   * @return give a task to an user (extra table in data base)
   */
  public boolean addTaskToUser(User user, Task task);
}
