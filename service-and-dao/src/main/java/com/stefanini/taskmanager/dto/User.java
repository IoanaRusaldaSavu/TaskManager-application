package com.stefanini.taskmanager.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements java.io.Serializable {
  /** */
  private static final long serialVersionUID = -225885835546300951L;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "userName", unique = true)
  private String userName;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userId;

  public User() {}

  public User(String firstName, String lastName, String userName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userid) {
    this.userId = userid;
  }

  @Override
  public String toString() {
    return String.format(
        "FirstName: "
            + this.firstName
            + " LastName: "
            + this.lastName
            + " UserName: "
            + this.userName);
  }
}
