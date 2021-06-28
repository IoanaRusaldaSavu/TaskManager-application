package com.stefanini.taskmanager.dao.task;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.config.HibernateConfig;
import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.dto.Task;

public class HibernateTaskDAO implements TaskDAO {
  private Logger logger = LogManager.getLogger(HibernateTaskDAO.class);
  private static HibernateTaskDAO hibernateTaskDAO;

  private HibernateTaskDAO() {}

  public static HibernateTaskDAO getInstanceTaskDAO() {
    if (hibernateTaskDAO == null) hibernateTaskDAO = new HibernateTaskDAO();
    return hibernateTaskDAO;
  }

  @Override
  public Task createTask(Task task) {
    if (findTaskByTitle(task.getTaskTitle()) != null) {
      logger.error("Task already exists");
      return null;
    }

    EntityManager em = HibernateConfig.createEntity();
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
      logger.error(ex);
    }
    return task;
  }

  @Override
  public List<Task> getTasks() {

    List<Task> tasks = new ArrayList<Task>();
    EntityManager em = HibernateConfig.createEntity();
    String query = "FROM Task";
    TypedQuery<Task> tq = em.createQuery(query, Task.class);
    try {
      tasks = tq.getResultList();
    } catch (NoResultException ex) {
      logger.error(ex);
    }
    return tasks;
  }

  @Override
  public Task findTaskByTitle(String title) {
    EntityManager em = HibernateConfig.createEntity();

    String query = "FROM Task t where title = :title";
    TypedQuery<Task> tq = em.createQuery(query, Task.class);
    tq.setParameter("title", title);
    Task task = null;
    try {
      task = tq.getSingleResult();
    } catch (NoResultException ex) {
      logger.error(ex);
    }
    return task;
  }
}
