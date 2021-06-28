package com.stefanini.taskmanager.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task implements java.io.Serializable {

  private static final long serialVersionUID = -1439938994130322629L;

  @Column(name = "title", unique = true)
  private String taskTitle;

  @Column(name = "description")
  private String description;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "taskId")
  private Integer taskId;

  @ManyToMany(mappedBy = "tasks")
  private Set<User> users = new HashSet<User>();

  public Task() {}

  public Task(String taskTitle, String description) {
    this.taskTitle = taskTitle;
    this.description = description;
  }

  public Task(String taskTitle, String description, Integer taskId) {
    this.taskTitle = taskTitle;
    this.description = description;
    this.taskId = taskId;
  }

  public String getTaskTitle() {
    return taskTitle;
  }

  public void setTaskTitle(String taskTitle) {
    this.taskTitle = taskTitle;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getTaskId() {
    return taskId;
  }

  public void setTaskId(Integer taskId) {
    this.taskId = taskId;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return String.format(
        "TaskTitle: " + taskTitle + " Description: " + description + "TaskId: " + taskId);
  }
}
