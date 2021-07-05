package com.stefanini.taskmanager.config;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CriteriaConfig {
  private CriteriaBuilder cb;
  private CriteriaQuery<? extends Object> cr;
  private Root<? extends Object> root;

  public CriteriaConfig(CriteriaBuilder cb, Class<? extends Object> cl) {
    this.cb = cb;
    cr = cb.createQuery(cl);
    root = cr.from(cl);
  }

  public CriteriaBuilder getCb() {
    return cb;
  }

  public void setCb(CriteriaBuilder cb) {
    this.cb = cb;
  }

  public CriteriaQuery<? extends Object> getCr() {
    return cr;
  }

  public void setCr(CriteriaQuery<? extends Object> cr) {
    this.cr = cr;
  }

  public Root<? extends Object> getRoot() {
    return root;
  }

  public void setRoot(Root<? extends Object> root) {
    this.root = root;
  }
}
