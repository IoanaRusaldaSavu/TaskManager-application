package com.stefanini.taskmanager.dao;

import java.util.List;

import com.stefanini.taskmanager.dto.Task;
/**
 * TODO:description
 *
 * @author isavu1
 */
public interface TaskDAO {
  /**
   * @param task to be added to the application
   * @return null - if an error appeared or task with same title already exists task - if was added
   *     successfully
   */
  public Task createTask(Task task);
  /** @return null if there are no tasks added */
  public List<Task> getTasks();
  /**
   * @param title
   * @return null if there are no tasks with specified title, else return the task
   */
  public Task findTaskByTitle(String title);
}
