package com.stefanini.taskmanager.dao;

import java.sql.Connection;

import com.stefanini.taskmanager.dao.user.DBUserDAO;
import com.stefanini.taskmanager.dao.user.FileUserDAO;
import com.stefanini.taskmanager.dao.user.HibernateUserDAO;

public class DAOUserFactory {
  public static UserDAO getDAO(String DAOType) {
    if (DAOType == null) {
      return null;
    }
    if (DAOType.equals("jdbc")) {
      Connection connection = ConnectionUtil.getConnection();
      return DBUserDAO.getInstanceUserDAO(connection);
    } else if (DAOType.equals("File")) {
      return new FileUserDAO();
    } else if (DAOType.equals("hibernate")) {
      return HibernateUserDAO.getInstanceUserDAO();
    }

    return null;
  }
}
