package com.stefanini.taskmanager.service;

import java.util.stream.Stream;

import com.stefanini.taskmanager.dto.Task;
/**
 * This interface provides services for Task
 * @author isavu1
 */
public interface TaskService {
  /** @param task - create a new task */
  public void createTask(Task task);
  /** show all tasks of application */
  public Stream<Task> getTasks();
}
