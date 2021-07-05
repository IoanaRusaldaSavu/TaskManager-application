package com.stefanini.taskmanager.service.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.stefanini.taskmanager.config.HibernateConfig;

public class TransactionProxy implements java.lang.reflect.InvocationHandler {
  private Object obj;

  public static Object newInstance(Object obj) {
    return java.lang.reflect.Proxy.newProxyInstance(
        obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new TransactionProxy(obj));
  }

  private TransactionProxy(Object obj) {
    this.obj = obj;
  }

  @Override
  public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
    Object result;
    EntityTransaction et = null;
    try {
      EntityManager em = HibernateConfig.getInstanceEntityManager();
      et = em.getTransaction();
      et.begin();
      result = m.invoke(obj, args);
      et.commit();
    } catch (InvocationTargetException e) {
      throw e.getTargetException();
    } catch (Exception e) {
      if (et != null) {
        et.rollback();
      } // TODO:custom exception
      throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
    }
    return result;
  }
}
