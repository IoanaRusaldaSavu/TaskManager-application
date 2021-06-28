package com.stefanini.taskmanager.dao.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.config.HibernateConfig;
import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dto.User;

public class HibernateUserDAO implements UserDAO {
  private static HibernateUserDAO hibernateUserDAO;

  private static final Logger logger = LogManager.getLogger(DBUserDAO.class);

  private HibernateUserDAO() {}

  public static HibernateUserDAO getInstanceUserDAO() {
    if (hibernateUserDAO == null) {
      hibernateUserDAO = new HibernateUserDAO();
    }
    return hibernateUserDAO;
  }

  @Override
  public User createUser(User user) {
    if (findUserByUserName(user.getUserName()) != null) {
      logger.error("This username already exists");
      return null;
    }
    EntityManager em = HibernateConfig.createEntity();
    EntityTransaction et = null;
    try {
      et = em.getTransaction();
      et.begin();
      em.persist(user);
      et.commit();
    } catch (Exception ex) {
      if (et != null) {
        et.rollback();
      }
      logger.error(ex);
    }
    return user;
  }

  @Override
  public List<User> getUsers() {
    List<User> users = new ArrayList<User>();
    EntityManager em = HibernateConfig.createEntity();
    String query = "FROM User";
    TypedQuery<User> tq = em.createQuery(query, User.class);
    try {
      users = tq.getResultList();

    } catch (NoResultException ex) {
      logger.error(ex);
    }
    return users;
  }
  public User findUserByUserName(String userName) {
    EntityManager em = HibernateConfig.createEntity();
    String query = "FROM User u where userName = :userName";
    TypedQuery<User> tq = em.createQuery(query, User.class);
    tq.setParameter("userName", userName);
    User user = null;
    try {
      user = tq.getSingleResult();
    } catch (NoResultException ex) {
      logger.error(ex);
    }
    return user;
  }
}
