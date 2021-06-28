package com.stefanini.taskmanager.dao.usertask;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.config.HibernateConfig;
import com.stefanini.taskmanager.dao.UserTaskDAO;
import com.stefanini.taskmanager.dto.Task;
import com.stefanini.taskmanager.dto.User;

public class HibernateUserTaskDAO implements UserTaskDAO {
  private static HibernateUserTaskDAO hibernateUserTaskDAO;
  private static final Logger logger = LogManager.getLogger(HibernateUserTaskDAO.class);

  private HibernateUserTaskDAO() {}

  public static HibernateUserTaskDAO getInstanceDAO() {
    if (hibernateUserTaskDAO == null) {
      hibernateUserTaskDAO = new HibernateUserTaskDAO();
	    }
    return hibernateUserTaskDAO;
	  }

  @Override
  public boolean addTaskToUser(User user, Task task) {
    if (verifyTaskGiven(user.getUserId(), task.getTaskId())) {
      logger.error("User " + user.getUserId() + " already has this task");
      return false;
    }
    EntityManager em = HibernateConfig.createEntity();
    EntityTransaction et = null;
    try {
      et = em.getTransaction();
      et.begin();
      user = em.find(User.class, user.getUserId());
      task = em.find(Task.class, task.getTaskId());
      user.addTask(task);
      em.persist(user);
      et.commit();
      return true;
    } catch (Exception ex) {
      if (et != null) {
        et.rollback();
      }
      logger.error(ex);

      return false;
    }
  }
  // TODO: class
  class UserTask {
    Integer userId;
    Integer taskId;

    public Integer getUserId() {
      return userId;
    }

    public void setUserId(Integer userId) {
      this.userId = userId;
    }

    public Integer getTaskId() {
      return taskId;
    }

    public void setTaskId(Integer taskId) {
      this.taskId = taskId;
    }
  }

  private boolean verifyTaskGiven(Integer userId, Integer taskId) {
    // TODO:createEntitymanager - rename
    EntityManager em = HibernateConfig.createEntity();
    String query =
        "select userId, taskId from users_tasks where taskId = :taskId and userId = :userId";
    // Query tq = em.createNativeQuery(query, UserTask.class);
    Query tq = em.createNativeQuery(query);
    tq.setParameter("taskId", taskId);
    tq.setParameter("userId", userId);
    try {
      // TODO:map - test
      // UserTask userTask = (UserTask)
      tq.getSingleResult();
      return true;
    } catch (NoResultException ex) {
      logger.error(ex);
      return false;
    }
  }
}
