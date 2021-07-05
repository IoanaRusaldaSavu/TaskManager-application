package com.stefanini.taskmanager.dao;

import java.util.stream.Stream;

import com.stefanini.taskmanager.dto.User;
/**
 * This interface interacts with User entity
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
  public Stream<User> getUsers();
  /**
   * @param userName
   * @return null if there are no user with specified userName, else return the User
   */
  public User findUserByUserName(String userName);
}
