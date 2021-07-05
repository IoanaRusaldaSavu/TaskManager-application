package com.stefanini.taskmanager.service;

import java.util.List;

import com.stefanini.taskmanager.dto.Task;
import com.stefanini.taskmanager.dto.User;
import com.stefanini.taskmanager.dto.UserTask;
import com.stefanini.taskmanager.dto.annotations.EmailGenerator;

/**
 * This interface provides services for User-Task relation
 *
 * @author isavu1
 */
public interface UserTaskService {
  /**
   * @param userName of user receiving the task
   * @param title of task given
   */
  @EmailGenerator
  public UserTask addTaskToUser(String userName, String title);
  /**
   * @param user - user receiving the tasks
   * @param tasks - tasks given
   */
  public void createUserAndAddTasks(User user, List<Task> tasks);
}
