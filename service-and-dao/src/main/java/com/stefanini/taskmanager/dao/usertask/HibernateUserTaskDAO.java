package com.stefanini.taskmanager.dao.usertask;

import javax.persistence.EntityManager;
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
  private EntityManager em = HibernateConfig.getInstanceEntityManager();

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
    user = em.find(User.class, user.getUserId());
    task = em.find(Task.class, task.getTaskId());
    user.addTask(task);
    em.persist(user);

    return true;
  }

  private boolean verifyTaskGiven(Integer userId, Integer taskId) {
    String query =
        "select userId, taskId from users_tasks where taskId = :taskId and userId = :userId";
    // TODO:custom criteria
    Query tq = em.createNativeQuery(query);
    tq.setParameter("taskId", taskId);
    tq.setParameter("userId", userId);
    try {
      // TODO:map - test with UserTask entity
      tq.getSingleResult();
      return true;
    } catch (NoResultException ex) {
      // TODO:to log or not?
      return false;
    }
  }
}
