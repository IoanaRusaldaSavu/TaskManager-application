package com.stefanini.taskmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.config.ApplicationProperties;

public class ConnectionUtil {

  private static Connection connection;
  private static final Logger logger = LogManager.getLogger(ConnectionUtil.class);
  private static final ApplicationProperties prop =
      ApplicationProperties.getApplicationProperties();
  /** @return connection to database */
  public static Connection getConnection() {
    if (connection == null)
      try {
        connection =
            DriverManager.getConnection(prop.getUrl(), prop.getUsername(), prop.getPassword());
      } catch (SQLException e) {
        logger.error(e);
      }
    return connection;
  }
  // TODO:close connection
}
