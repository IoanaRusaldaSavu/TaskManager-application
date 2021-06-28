package com.stefanini.taskmanager.dao.task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.dto.Task;

public class DBTaskDAO implements TaskDAO {
  private Connection connection;
  private static DBTaskDAO taskDAO;
  private Logger logger = LogManager.getLogger(DBTaskDAO.class);


  private DBTaskDAO(Connection connection) {
    this.connection = connection;
  };

  public static DBTaskDAO getInstanceTaskDAO(Connection connection) {
    if (taskDAO == null) {
      taskDAO = new DBTaskDAO(connection);
    }
    return taskDAO;
  }

  public Task createTask(Task task) {
    if (isUniqueTask(task.getTaskTitle())) {
      try {
        PreparedStatement p;
        p =
            connection.prepareStatement(
                "insert into tasks (title, description) values(?,?)",
                Statement.RETURN_GENERATED_KEYS);
        p.setString(1, task.getTaskTitle());
        p.setString(2, task.getDescription());
        int i = p.executeUpdate();
        if (i > 0) {
          logger.info(i + " records inserted");
        }

        ResultSet rs = p.getGeneratedKeys();
        if (rs.next()) {
          task.setTaskId(rs.getInt(1));
        }
      } catch (SQLException e) {
        logger.error(e);
        return null;
      }

    } else {
      logger.error("Task " + task.getTaskTitle() + " already exist");
      return null;
    }
    return task;
  }

  private boolean isUniqueTask(String title) {
    String query = "select title from tasks where title = ?";
    PreparedStatement p;
    try {
      p = connection.prepareStatement(query);
      p.setString(1, title);
      ResultSet rs = p.executeQuery();
      if (rs.next()) {
        return false;
      }
    } catch (SQLException e) {
      logger.error(e);
      return false;
    }

    return true;
  }

  public List<Task> getTasks() {
    List<Task> tasks = new ArrayList<Task>();
    try {
      Statement s = connection.createStatement();
      ResultSet rs = s.executeQuery("select title, description from tasks");
      while (rs.next()) {
        tasks.add(new Task(rs.getString(1), rs.getString(2)));
      }

    } catch (SQLException e) {
      logger.error(e);
    }
    return tasks;
  }

  public Task findTaskByTitle(String title) {
    String query = "select title, description, taskId from tasks where title = ?";
    PreparedStatement p;
    try {
      p = connection.prepareStatement(query);
      p.setString(1, title);
      ResultSet rs = p.executeQuery();

      if (rs.next()) {
        return new Task(rs.getString(1), rs.getString(2), rs.getInt(3));
      }
    } catch (SQLException e) {
      logger.error(e);
      return null;
    }
    return null;
  }
}
