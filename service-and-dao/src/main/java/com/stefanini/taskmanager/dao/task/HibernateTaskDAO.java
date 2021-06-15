package com.stefanini.taskmanager.dao.task;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.dto.Task;

public class HibernateTaskDAO implements TaskDAO {
	  private static EntityManagerFactory entity =
		      Persistence.createEntityManagerFactory("task-manager");
  private static HibernateTaskDAO hibernateTaskDAO;

  private HibernateTaskDAO() {}

  public static HibernateTaskDAO getInstanceTaskDAO() {
    if (hibernateTaskDAO == null) hibernateTaskDAO = new HibernateTaskDAO();
    return hibernateTaskDAO;
  }

  @Override
  public Task createTask(Task task) {
    // TODO Auto-generated method stub
		  EntityManager em = entity.createEntityManager();
		    EntityTransaction et = null;
		    try {
		      et = em.getTransaction();
		      et.begin();
      em.persist(task);
		      et.commit();
		    } catch (Exception ex) {
		      if (et != null) {
		        et.rollback();
		      }
		      ex.printStackTrace();
		    } finally {
		      em.close();
		    }
    return task;
  }
  

  @Override
  public List<Task> getTasks() {

    List<Task> tasks = new ArrayList<Task>();
    EntityManager em = entity.createEntityManager();
    String query = "select t FROM Task t";
    // SELECT c FROM Customer c WHERE c.id IS NOT NULL

    // Issue the query and get a matching Customer
    TypedQuery<Task> tq = em.createQuery(query, Task.class);
    // tq.setParameter("custID", id);

    // Customer cust = null;
    // User user = null;
    try {
      // Get matching customer object and output
      //  user = tq.getSingleResult();
      tasks = tq.getResultList();
      // System.out.println(cust.getFName() + " " + cust.getLName());
      // users.add(user);
    } catch (NoResultException ex) {
      ex.printStackTrace();
    } finally {
      em.close();
    }

    return tasks;
  }}
