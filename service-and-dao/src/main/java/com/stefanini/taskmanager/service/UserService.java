package com.stefanini.taskmanager.service;

import java.util.List;

import com.stefanini.taskmanager.dto.User;
/**
 * TODO:description
 *
 * @author isavu1
 */
public interface UserService {
  /** @param user - create new user in application */
  public void createUser(User user);
  /** show all users from application */
  public List<User> getUsers();
  /*  */
  /**
   * @param userName of user receiving the task
   * @param title of task given
   */
  /*
  public void addTaskToUser(String userName, String title);*/
}
