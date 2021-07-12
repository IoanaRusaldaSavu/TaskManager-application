package com.stefanini.taskmanager.service.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.stefanini.taskmanager.dto.annotations.Email;
import com.stefanini.taskmanager.dto.annotations.EmailField;
import com.stefanini.taskmanager.dto.annotations.EmailGenerator;

public class EmailProxy implements java.lang.reflect.InvocationHandler {
  private final Object obj;

  public static Object newInstance(Object obj) {
    return java.lang.reflect.Proxy.newProxyInstance(
        obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new EmailProxy(obj));
  }

  private EmailProxy(Object obj) {
    this.obj = obj;
  }

  public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
    Object result = m.invoke(obj, args);
    if (result != null) {
      Class<? extends Object> resultClass = result.getClass();
      if (resultClass.isAnnotationPresent(Email.class)
          && m.isAnnotationPresent(EmailGenerator.class)) {
        String emailTemplate = resultClass.getAnnotation(Email.class).message();
        String email = buildEmail(result, emailTemplate);
        System.out.println(email);
      }
    }
    return result;
  }

  private String buildEmail(Object result, String email)
      throws IllegalArgumentException, IllegalAccessException {
    for (Field f : result.getClass().getDeclaredFields()) {
      if (f.isAnnotationPresent(EmailField.class)) {
        String value = f.getAnnotation(EmailField.class).field();
        f.setAccessible(true);
        email = email.replaceAll(value, String.valueOf(f.get(result)));
        f.setAccessible(false);
      }
    }
    return email;
  }
}
