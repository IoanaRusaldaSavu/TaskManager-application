package com.stefanini.taskmanager;

public interface Operations extends Runnable {
  void execute();

  @Override
  public default void run() {
    this.execute();
  }

  public String getName();
}
