package com.stefanini.taskmanager;

import java.util.Scanner;

import com.stefanini.taskmanager.commands.AddTaskToUser;
import com.stefanini.taskmanager.commands.CreateTask;
import com.stefanini.taskmanager.commands.CreateUser;
import com.stefanini.taskmanager.commands.ShowTasks;
import com.stefanini.taskmanager.commands.ShowUsers;
import com.stefanini.taskmanager.dto.Task;
import com.stefanini.taskmanager.dto.User;

public class Main {
  public static void main(String[] args) {
    // TODO: close DB connection
    CommandExecutor executor = CommandExecutor.getExecutor();
    System.out.println("Enter command:");
    Scanner scan = new Scanner(System.in);
    int finished = 0;
    String command = null;
    while (true) {
      command = scan.next();
      if (command.equals("createUser")) {
        System.out.println("Enter firstName:");
        String firstName = scan.next();
        System.out.println("Enter lastName:");
        String lastName = scan.next();
        System.out.println("Enter userName:");
        String userName = scan.next();
        CreateUser createUser = new CreateUser(new User(firstName, lastName, userName));
        executor.addOperation(createUser);
      }
      if (command.equals("showUsers")) {
        ShowUsers showUsers = new ShowUsers();
        executor.addOperation(showUsers);
      }


      if (command.equals("createTask")) {
        System.out.println("Enter title:");
        String title = scan.next();
        System.out.println("Enter description:");
        String description = scan.next();
        CreateTask createTask = new CreateTask(new Task(title, description));
        executor.addOperation(createTask);
      }
      if (command.equals("showTasks")) {
        ShowTasks showTasks = new ShowTasks();
        executor.addOperation(showTasks);
      }
      if (command.equals("addTaskToUser")) {
        System.out.println("Enter userName:");
        String userName = scan.next();
        System.out.println("Enter title:");
        String title = scan.next();
        AddTaskToUser addTaskToUserCommand = new AddTaskToUser(userName, title);
        executor.addOperation(addTaskToUserCommand);
      }
      if (command.equals("finish")) {
        break;
      }
      // TODO:createUserAndAddTask

      System.out.println("Enter command:");
    }
    executor.execute();
    System.out.println("Finished");
  }
}
