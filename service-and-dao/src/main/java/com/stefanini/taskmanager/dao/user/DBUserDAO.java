package com.stefanini.taskmanager.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dto.User;

public class DBUserDAO implements UserDAO {
  private Connection connection;
  private static DBUserDAO userDAO;
  private static final Logger logger = LogManager.getLogger(DBUserDAO.class);
  

  private DBUserDAO(Connection connection) {
    this.connection = connection;
  }

  public static DBUserDAO getInstanceUserDAO(Connection connection) {
    if (userDAO == null) {
      userDAO = new DBUserDAO(connection);
    }
    return userDAO;
  }



  public User createUser(User user) {
    String insertQuery = "insert into Users (firstName, lastName, userName) values(?,?,?)";
    if (isUniqueUsername(user.getUserName())) {
      try {
        PreparedStatement p;
        p = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        p.setString(1, user.getFirstName());
        p.setString(2, user.getLastName());
        p.setString(3, user.getUserName());
        int i = p.executeUpdate();
        if (i > 0) {
          logger.info(i + " records inserted");
        }
        ResultSet rs = p.getGeneratedKeys();
        if (rs.next()) {
          user.setUserId(rs.getInt(1));
        }
      } catch (SQLException e) {
        logger.error(e);
        return null;
      }

    } else {
      logger.error("UserName " + user.getUserName() + " already used");
      return null;
    }
    return user;
  }

  private boolean isUniqueUsername(String userName) {
    String query = "select userName from users where userName = ?";
    PreparedStatement p;
    try {
      p = connection.prepareStatement(query);
      p.setString(1, userName);
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

  public Stream<User> getUsers() {
    List<User> users = new ArrayList<User>();
    try {
      Statement s = connection.createStatement();
      ResultSet rs = s.executeQuery("select firstName, lastName, userName from users");
      while (rs.next()) {
        users.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
      }

      
    } catch (SQLException e) {
      logger.error(e);
      return null;
    }
    return users.stream();
  }

  public User findUserByUserName(String userName) {
    String query = "select firstName, lastName, userName, userId from users where userName = ?";
    PreparedStatement p;
    try {
      p = connection.prepareStatement(query);
      p.setString(1, userName);
      ResultSet rs = p.executeQuery();

      if (rs.next()) {
        return new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
      }
    } catch (SQLException e) {
      logger.error(e);
      return null;
    }
    return null;
  }

}
