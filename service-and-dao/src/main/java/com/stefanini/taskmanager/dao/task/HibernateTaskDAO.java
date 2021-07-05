package com.stefanini.taskmanager.dao.task;

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

import com.stefanini.taskmanager.config.CriteriaConfig;
import com.stefanini.taskmanager.config.HibernateConfig;
import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.dto.Task;

public class HibernateTaskDAO implements TaskDAO {
  private Logger logger = LogManager.getLogger(HibernateTaskDAO.class);
  private static HibernateTaskDAO hibernateTaskDAO;
  private EntityManager em = HibernateConfig.getInstanceEntityManager();
  private CriteriaBuilder cb = em.getCriteriaBuilder();

  private CriteriaConfig criteria = new CriteriaConfig(em.getCriteriaBuilder(), Task.class);

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
    EntityManager em = HibernateConfig.getInstanceEntityManager();
    em.persist(task);
    return task;
  }

  @Override
  public Stream<Task> getTasks() {

    List<Task> tasks = new ArrayList<Task>();
    CriteriaQuery<Task> cr = cb.createQuery(Task.class);
    Root<Task> root = cr.from(Task.class);
    cr.select(root);
    TypedQuery<Task> tq = em.createQuery(cr);
    try {
      tasks = tq.getResultList();
    } catch (NoResultException ex) {
      logger.error(ex);
    }
    return tasks.stream();
  }

  @Override
  public Task findTaskByTitle(String title) {
    CriteriaQuery<Task> cr = cb.createQuery(Task.class);
    Root<Task> root = cr.from(Task.class);
    cr.select(root).where(cb.equal(root.get("taskTitle"), title));
    TypedQuery<Task> tq = em.createQuery(cr);
    Task task = null;
    try {
      task = tq.getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
    return task;
  }
}
