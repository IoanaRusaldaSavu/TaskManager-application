package com.stefanini.taskmanager.applicationLogic;

import java.util.ArrayList;
import java.util.List;

import com.stefanini.taskmanager.Operations;
import com.stefanini.taskmanager.commands.AddTaskToUser;
import com.stefanini.taskmanager.commands.CreateTask;
import com.stefanini.taskmanager.commands.CreateUser;

public class ExecutorLogic {
  private List<Operations> level1 = new ArrayList<Operations>();
  private List<Operations> level2 = new ArrayList<Operations>();
  private List<Operations> level3 = new ArrayList<Operations>();

  public void sortOperation(List<Operations> operations) {
    for (Operations op : operations) {
      if (op.getName().equals("createUser") || op.getName().equals("createTask")) {
        level1.add(op);
      }
      if (op.getName().equals("showUsers") || op.getName().equals("showTasks")) {
        level3.add(op);
      }
    }
    for (Operations op : operations) {
      if (op.getName().equals("addTaskToUser")) {
        if (!isDependingOn(op)) {
          level1.add(op);
        } else {
          level2.add(op);
        }
      }
    }
    print();
  }

  private boolean isDependingOn(Operations operation) {
    AddTaskToUser addTaskToUser = (AddTaskToUser) operation;
    for (Operations op : level1) {
      if (op.getName().equals("createUser")) {
        CreateUser createUser = (CreateUser) op;
        if (createUser.getUser().getUserName().equals(addTaskToUser.getUserName())) {
          return true;
        }
      }
      if (op.getName().equals("createTask")) {
        CreateTask createTask = (CreateTask) op;
        if (createTask.getTask().getTaskTitle().equals(addTaskToUser.getTitle())) {
          return true;
        }
      }
    }
    return false;
  }

  private void print() {
    System.out.println("LEVEL1");
    for (Operations op : level1) {
      System.out.println(op.getName());
    }
    System.out.println("LEVEL2");
    for (Operations op : level2) {
      System.out.println(op.getName());
    }
    System.out.println("LEVEL3");
    for (Operations op : level3) {
      System.out.println(op.getName());
    }
  }

  public List<Operations> getLevel1() {
    return level1;
  }

  public List<Operations> getLevel2() {
    return level2;
  }

  public List<Operations> getLevel3() {
    return level3;
  }
}
// showTasks, showUsers,
// addTaskToUser(user1,Task1),createUser(user2),addTaskToUser(user2,Task2),createTask(Task3),showUsers,addTaskToUser(user5,task2),showsTasks
