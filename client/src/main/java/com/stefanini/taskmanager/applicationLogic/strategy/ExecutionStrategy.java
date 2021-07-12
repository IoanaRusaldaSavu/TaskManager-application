package com.stefanini.taskmanager.applicationLogic.strategy;

import java.util.List;

import com.stefanini.taskmanager.Operations;

public interface ExecutionStrategy {
  void execute(List<Operations> op);
}
