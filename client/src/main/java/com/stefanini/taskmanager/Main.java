package com.stefanini.taskmanager;

import com.stefanini.taskmanager.commands.AddTaskToUser;
import com.stefanini.taskmanager.commands.CreateTask;
import com.stefanini.taskmanager.commands.CreateUser;
import com.stefanini.taskmanager.commands.ShowTasks;
import com.stefanini.taskmanager.commands.ShowUsers;
import com.stefanini.taskmanager.dto.Task;
import com.stefanini.taskmanager.dto.User;

public class Main {
  // TODO:denumire metode
  public static void main(String[] args) {
    // TODO: close DB connection
    CommandExecutor executor = CommandExecutor.getExecutor();
    
    if (args[0].equals("-createUser")) {
      CreateUser createUserCommand =
          new CreateUser(
              new User(args[1].substring(4), args[2].substring(4), args[3].substring(4)));
      executor.addOperation(createUserCommand);
    }

    if (args[0].equals("-showAllUsers")) {
      ShowUsers showUsersCommand = new ShowUsers();
      executor.addOperation(showUsersCommand);
    }
    if (args[0].equals("-createTask")) {
      CreateTask createTaskCommand =
          new CreateTask(new Task(args[1].substring(4), args[2].substring(4)));
      executor.addOperation(createTaskCommand);
    }
    if (args[0].equals("-showTasks")) {
      ShowTasks showTasksCommand = new ShowTasks();
      executor.addOperation(showTasksCommand);
    }
    // TODO:remove addTask + no identify by title
    if (args[0].equals("-addTaskToUser")) {
      AddTaskToUser addTaskToUserCommand =
          new AddTaskToUser(args[1].substring(4), args[2].substring(4));
      executor.addOperation(addTaskToUserCommand);
    }
    executor.execute();
  }
}
