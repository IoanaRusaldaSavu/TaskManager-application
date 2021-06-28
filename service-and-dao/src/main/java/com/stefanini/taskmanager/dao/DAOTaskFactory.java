package com.stefanini.taskmanager.dao;

import java.sql.Connection;

import com.stefanini.taskmanager.dao.task.DBTaskDAO;
import com.stefanini.taskmanager.dao.task.FileTaskDAO;
import com.stefanini.taskmanager.dao.task.HibernateTaskDAO;

public class DAOTaskFactory {
  public static TaskDAO getDAO(String DAOType) {
    if (DAOType == null) {
      return null;
    }
    if (DAOType.equals("jdbc")) {
      Connection connection = ConnectionUtil.getConnection();
      return DBTaskDAO.getInstanceTaskDAO(connection);

    } else if (DAOType.equals("File")) {
      return new FileTaskDAO();
    } else if (DAOType.equals("hibernate")) {
      return HibernateTaskDAO.getInstanceTaskDAO();
    }
    return null;
  }
}
