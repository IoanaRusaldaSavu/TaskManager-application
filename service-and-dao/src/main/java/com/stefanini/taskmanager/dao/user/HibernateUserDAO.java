package com.stefanini.taskmanager.dao.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dto.User;

public class HibernateUserDAO implements UserDAO {
  private static EntityManagerFactory entity =
      Persistence.createEntityManagerFactory("task-manager");
  private static HibernateUserDAO hibernateUserDAO;

  private HibernateUserDAO() {}

  public static HibernateUserDAO getInstanceUserDAO() {
    if (hibernateUserDAO == null) {
      hibernateUserDAO = new HibernateUserDAO();
    }
    return hibernateUserDAO;
  }

  @Override
  public User createUser(User user) {
	  EntityManager em = entity.createEntityManager();
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
	      ex.printStackTrace();
	      
	    } finally {
	      em.close();
	    }
    return user;
  }

  @Override
  public List<User> getUsers() {
    // TODO Auto-generated method stub
    List<User> users = new ArrayList<User>();
    EntityManager em = entity.createEntityManager();
    String query = "select u FROM User u";
   // SELECT c FROM Customer c WHERE c.id IS NOT NULL

    // Issue the query and get a matching Customer
    TypedQuery<User> tq = em.createQuery(query, User.class);
    // tq.setParameter("custID", id);

    // Customer cust = null;
    // User user = null;
    try {
      // Get matching customer object and output
      //  user = tq.getSingleResult();
      users = tq.getResultList();
      // System.out.println(cust.getFName() + " " + cust.getLName());
      // users.add(user);
    } catch (NoResultException ex) {
      ex.printStackTrace();
    } finally {
      em.close();
    }

    return users;
  }

  @Override
  public boolean addTask(String userName, String title) {
    // TODO Auto-generated method stub
    return false;
  }

}
