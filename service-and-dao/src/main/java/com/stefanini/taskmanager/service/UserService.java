package com.stefanini.taskmanager.service;

import java.util.List;

import com.stefanini.taskmanager.dto.User;
import com.stefanini.taskmanager.dto.annotations.EmailGenerator;
/**
 * This interface provides services for User
 * @author isavu1
 */
public interface UserService {
  /** @param user - create new user in application */
  @EmailGenerator
  public User createUser(User user);
  /** show all users from application */
  public List<User> getUsers();
}
