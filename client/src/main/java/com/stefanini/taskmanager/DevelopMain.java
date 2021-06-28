package com.stefanini.taskmanager;

public class DevelopMain {
  // private static final Logger logger = LogManager.getLogger(Main.class);

  public static void main(String[] args) {

    /*  HIBERNATE CREATE USER
     * UserService userServ = ServiceFactory.getUserService();
    User user = new User("Ioana", "Dima", "ID");
    userServ.createUser(user);*/

    /*HIBERNATE CREATE TASK
    TaskService taskServ = ServiceFactory.getTaskService();
    Task task = new Task("Task6", "Task6 Description");
    taskServ.createTask(task);*/

    /* HIBERNATE ADD TASK TO USER
    UserTaskService service = ServiceFactory.getUserTaskService();
    service.addTaskToUser("ID", "Task5");*/
    // COMPOSITE KEY
    //  UserTaskService service = ServiceFactory.getUserTaskService();
    //  service.addTaskToUser("Amp", "Task5");
  }
}
