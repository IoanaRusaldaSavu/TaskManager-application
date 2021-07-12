package com.stefanini.taskmanager.dao.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.config.HibernateConfig;
import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dto.User;

public class HibernateUserDAO implements UserDAO {
  private static HibernateUserDAO hibernateUserDAO;

  private static final Logger logger = LogManager.getLogger(DBUserDAO.class);

  private EntityManager em = HibernateConfig.getInstanceEntityManager();
  private CriteriaBuilder cb = em.getCriteriaBuilder();

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
    em.persist(user);
    return user;
  }

  @Override
  public Stream<User> getUsers() {
    List<User> users = new ArrayList<User>();
    CriteriaQuery<User> cr = cb.createQuery(User.class);
    Root<User> root = cr.from(User.class);
    cr.select(root);
    TypedQuery<User> tq = em.createQuery(cr);
    try {
      users = tq.getResultList();

    } catch (NoResultException ex) {
      logger.error(ex);
    }
    return users.stream();
  }
  
  public User findUserByUserName(String userName) {
    CriteriaQuery<User> cr = cb.createQuery(User.class);
    Root<User> root = cr.from(User.class);
    cr.select(root).where(cb.equal(root.get("userName"), userName));
    TypedQuery<User> tq = em.createQuery(cr);
    User user = null;
    try {
      user = tq.getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
    return user;
  }
}
