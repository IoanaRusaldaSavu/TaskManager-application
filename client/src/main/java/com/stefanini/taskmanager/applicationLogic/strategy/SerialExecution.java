package com.stefanini.taskmanager.applicationLogic.strategy;

import java.util.List;

import com.stefanini.taskmanager.Operations;

public class SerialExecution implements ExecutionStrategy {

  @Override
  public void execute(List<Operations> operations) {
    for (Operations op : operations) {
      op.execute();
    }
  }
}
