package com.stefanini.taskmanager;

public class DevelopMain {
  // private static final Logger logger = LogManager.getLogger(Main.class);

  public static void main(String[] args) {
    /*    System.out.println("=========WITH THREAD=========");
    User user = new User("fn", "ln", "BM");
    Task task = new Task("Task28", "Task28Description");
    CreateUser createUser = new CreateUser(user);
    CreateTask createTask = new CreateTask(task);
    AddTaskToUser add = new  AddTaskToUser (user.getUserName(), task.getTaskTitle());
    ShowUsers showUsers = new ShowUsers();
    Thread thread1 = new Thread(createUser);
    Thread thread2 = new Thread(createTask);
    Thread thread3 = new Thread(add);
    System.out.println(System.nanoTime());
    thread1.start();
    thread2.start();
    try {
      thread1.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    thread3.start();
    System.out.println(System.nanoTime());*/

    /*    System.out.println("=========WITHOUT THREAD=========");
    User user2 = new User("fn", "ln", "BI");
    Task task2 = new Task("Task25", "Task25Description");
    CreateUser createUser2 = new CreateUser(user2);
    CreateTask createTask2 = new CreateTask(task2);
    System.out.println(System.nanoTime());
    createUser2.execute();
    createTask2.execute();
    System.out.println(System.nanoTime());*/

    //   CommandExecutor executor = CommandExecutor.getExecutor();
    //   executor.sortOperation();
    /*    User user = new User("fn", "ln", "BN");
    Task task = new Task("Task29", "Task29Description");
    CreateUser createUser = new CreateUser(user);
    CreateTask createTask = new CreateTask(task);
    ShowUsers showUsers = new ShowUsers();
    ShowTasks showTasks = new ShowTasks();
    executor.addOperation(createUser);
    executor.addOperation(createTask);
    executor.addOperation(showUsers);
    executor.addOperation(showTasks);
    executor.execute();*/
  }
}
