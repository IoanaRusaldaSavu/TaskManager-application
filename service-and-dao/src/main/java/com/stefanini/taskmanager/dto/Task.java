package com.stefanini.taskmanager.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task implements java.io.Serializable {

  /** */
  private static final long serialVersionUID = -1439938994130322629L;

  @Column(name = "title")
  private String taskTitle;

  @Column(name = "description")
  private String description;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer taskId;

  public Task() {}

  public Task(String taskTitle, String description) {
    this.taskTitle = taskTitle;
    this.description = description;
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

  @Override
  public String toString() {
    return String.format("TaskTitle: " + this.taskTitle + " Description: " + this.description);
  }
}
