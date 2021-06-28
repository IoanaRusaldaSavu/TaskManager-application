package com.stefanini.taskmanager.dao;

import java.util.List;

import com.stefanini.taskmanager.dto.User;
/**
 * TODO:description
 *
 * @author isavu1
 */
public interface UserDAO {
  /**
   * @param user to be added to the application
   * @return null - if an error appeared or user with same username already exists user - if was
   *     added successfully
   */
  public User createUser(User user);
  /** @return null if there are no user added */
  public List<User> getUsers();
  /**
   * @param userName of user receiving the task
   * @param title of task given
   * @return false - if an error appeared or user already have the task ture - if was added
   *     successfully
   */
  /**
   * @param userName
   * @return null if there are no user with specified userName, else return the User
   */
  public User findUserByUserName(String userName);
}
