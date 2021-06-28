package com.stefanini.taskmanager.service;
/**
 * TODO:description
 *
 * @author isavu1
 */
public interface UserTaskService {
  /**
   * @param userName of user receiving the task
   * @param title of task given
   */
  public void addTaskToUser(String userName, String title);
}
