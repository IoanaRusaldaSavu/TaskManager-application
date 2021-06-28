package com.stefanini.taskmanager.dao;

import java.sql.Connection;

import com.stefanini.taskmanager.dao.usertask.DBUserTaskDAO;
import com.stefanini.taskmanager.dao.usertask.FileUserTaskDAO;
import com.stefanini.taskmanager.dao.usertask.HibernateUserTaskDAO;

public class DAOUserTaskFactory {
  public static UserTaskDAO getDAO(String DAOType) {
    if (DAOType == null) {
      return null;
    }
    if (DAOType.equals("hibernate")) {
      return HibernateUserTaskDAO.getInstanceDAO();
    }
    if (DAOType.equals("jdbc")) {
      Connection connection = ConnectionUtil.getConnection();
      return DBUserTaskDAO.getInstanceDAO(connection);
    }
    if (DAOType.equals("jdbc")) {
      return FileUserTaskDAO.getInstanceDAO();
    }
    return null;
  }
}
