package com.stefanini.taskmanager.service;

import java.util.List;

import com.stefanini.taskmanager.dto.Task;

public interface TaskService {
  /** @param task - create a new task */
  public void createTask(Task task);
  /** show all tasks of application */
  public List<Task> getTasks();
}
