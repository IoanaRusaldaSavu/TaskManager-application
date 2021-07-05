package com.stefanini.taskmanager.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateConfig {
  // TODO:close entity
  private static EntityManagerFactory entity =
      Persistence.createEntityManagerFactory("task-manager");
  private static EntityManager manager;
  private static HibernateConfig hibernateConfig;
  private HibernateConfig() {}

  public static HibernateConfig getHibernateConfig() {
    if (hibernateConfig == null) {
      hibernateConfig = new HibernateConfig();
    }
    return hibernateConfig;
  }

  public static EntityManager getInstanceEntityManager() {
    if (manager == null) {
      manager = entity.createEntityManager();
    }
    return manager;
  }
}
