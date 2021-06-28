package com.stefanini.taskmanager.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateConfig {
  private static EntityManagerFactory entity =
      Persistence.createEntityManagerFactory("task-manager");
  // TODO:close entity
  public static EntityManager createEntity() {
    EntityManager manager = entity.createEntityManager();
    return manager;
  }
}
