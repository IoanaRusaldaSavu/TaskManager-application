package com.stefanini.taskmanager.dto;

import com.stefanini.taskmanager.dto.annotations.Email;
import com.stefanini.taskmanager.dto.annotations.EmailField;

@Email(message = "Task {task title} {task description} was given to {userName}")
public class UserTask {
  @EmailField(field = "userName")
  private String userName;

  @EmailField(field = "task title")
  private String taskTitle;

  @EmailField(field = "task description")
  private String taskDescription;

  public UserTask(String userName, String taskTitle, String taskDescription) {
    super();
   
    this.userName = userName;
   
    this.taskTitle = taskTitle;
   
    this.taskDescription = taskDescription;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getTaskTitle() {
    return taskTitle;
  }

  public void setTaskTitle(String taskTitle) {
    this.taskTitle = taskTitle;
  }

  public String getTaskDescription() {
    return taskDescription;
  }

  public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
  }
}
