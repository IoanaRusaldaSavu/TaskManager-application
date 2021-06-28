package com.stefanini.taskmanager.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.dao.ConnectionUtil;

public class ApplicationProperties {
  private static final Logger logger = LogManager.getLogger(ApplicationProperties.class);
  private static ApplicationProperties applicationProperties;
  private String url;
  private String username;
  private String password;
  private String daoType;

  private ApplicationProperties() {
    Properties props = extractProperties();
    url = props.getProperty("jdbc.url");
    username = props.getProperty("jdbc.username");
    password = props.getProperty("jdbc.password");
    daoType = props.getProperty("service.type");
  }

  public static ApplicationProperties getApplicationProperties() {
    if (applicationProperties == null) {
      applicationProperties = new ApplicationProperties();
    }
    return applicationProperties;
  }

  public String getUrl() {
    return url;
  }
  
  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getDaoType() {
    return daoType;
  }
  /** @return properties extracted for application */
  private static Properties extractProperties() {
    Properties prop = new Properties();
    InputStream ip =
        ConnectionUtil.class.getClassLoader().getResourceAsStream("application.properties");
    try {
      prop.load(ip);
    } catch (IOException e) {
      logger.error(e);
    }
    return prop;
  }
}
