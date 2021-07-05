package com.stefanini.taskmanager;

import java.util.ArrayList;
import java.util.List;

import com.stefanini.taskmanager.commands.CreateUserAndAddTasks;
import com.stefanini.taskmanager.dto.Task;
import com.stefanini.taskmanager.dto.User;

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

    // AOP-with decorator
    /*    TaskService taskServ = ServiceFactory.getTaskService();
    Task task = new Task("Task7", "Task7Description");
    ServiceDecorator decorator = new ServiceDecorator(taskServ);
    decorator.createTask(task);*/

    // AOP-Proxy
    /*    TaskService taskServ = ServiceFactory.getTaskService();
    TaskService taskServ2 = (TaskService) AuditProxy.newInstance(taskServ);
    taskServ2.createTask(new Task("Task7", "Task7Description"));*/

    //    User user = new User("Andrei", "Savu", "AS");
    //    UserService userService = ServiceFactory.getUserService();
    //    UserService service = (UserService) EmailProxy.newInstance(userService);
    //    service.createUser(user);
    // TEST EMAiL
    /*    UserDAO userDao = DAOUserFactory.getDAO("hibernate");
    TaskDAO taskDao = DAOTaskFactory.getDAO("hibernate");
    UserTaskDAO userTaskDao = DAOUserTaskFactory.getDAO("hibernate");
    UserTaskService userTaskService =
        UserTaskServiceImpl.getUserTaskService(userDao, taskDao, userTaskDao);
    UserTaskService service = (UserTaskService) EmailProxy.newInstance(userTaskService);
    service.addTaskToUser("AS", "Task1");*/
	  
	  CommandExecutor executor = CommandExecutor.getExecutor();
    List<Task> tasks = new ArrayList<Task>();
    User user = new User("Mihnea", "Tanase", "MT");

    tasks.add(new Task("Task13", "Task13Description"));
    tasks.add(new Task("Task14", "Task14Description"));
    CreateUserAndAddTasks command = new CreateUserAndAddTasks(user, tasks);
    executor.addOperation(command);
    executor.execute();
  }
}
