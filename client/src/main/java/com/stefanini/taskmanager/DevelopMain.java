package com.stefanini.taskmanager;

import com.stefanini.taskmanager.commands.ShowTasks;

public class DevelopMain {
  // private static final Logger logger = LogManager.getLogger(Main.class);

  public static void main(String[] args) {

    CommandExecutor executor = CommandExecutor.getExecutor();
    //    User user = new User("d", "hhh", "iii");
    //    Operations operation = new CreateUser(user);
    //    ShowUsers showUsersCommand = new ShowUsers();
    //    executor.addOperation(showUsersCommand);
    //    CreateTask createTaskCommand = new CreateTask(new Task("T7", "Td7"));
    //    executor.addOperation(createTaskCommand);
    ShowTasks showTasksCommand = new ShowTasks();
    executor.addOperation(showTasksCommand);

    executor.execute();
  }
}
