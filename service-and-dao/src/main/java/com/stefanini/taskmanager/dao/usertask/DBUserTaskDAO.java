package com.stefanini.taskmanager.dao.usertask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.dao.UserTaskDAO;
import com.stefanini.taskmanager.dto.Task;
import com.stefanini.taskmanager.dto.User;

public class DBUserTaskDAO implements UserTaskDAO {
  private static final Logger logger = LogManager.getLogger(DBUserTaskDAO.class);
  private Connection connection;
  private static DBUserTaskDAO userTaskDAO;

  private DBUserTaskDAO(Connection connection) {
	    this.connection = connection;
	  }

  public static DBUserTaskDAO getInstanceDAO(Connection connection) {
    if (userTaskDAO == null) {
      userTaskDAO = new DBUserTaskDAO(connection);
	    }
    return userTaskDAO;
	  }

  @Override
  public boolean addTaskToUser(User user, Task task) {
    try {

      String query =
          "insert into users_tasks (userId, taskId) select ?, taskId from tasks where title = ?";
      PreparedStatement p = connection.prepareStatement(query);

      p.setInt(1, user.getUserId());
      p.setString(2, task.getTaskTitle());
      int i = p.executeUpdate();
      if (i > 0) {
        logger.info(i + " records inserted");
      }
      return true;
    } catch (SQLIntegrityConstraintViolationException e) {
      logger.error("User " + user.getUserId() + " already has this task ");
      return false;
    } catch (SQLException e) {

      logger.error(e);
      return false;
    }
  }
}
