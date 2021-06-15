package com.stefanini.taskmanager;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
  private List<Operations> operations = new ArrayList<Operations>();
  private static CommandExecutor executor;

  private CommandExecutor() {}

  public static CommandExecutor getExecutor() {
    if (executor == null) executor = new CommandExecutor();
    return executor;
  }

  public void addOperation(Operations op) {
    operations.add(op);
  }

  public void removeOperation(Operations op) {
    operations.remove(op);
  }

  public void execute() {
    for (Operations op : operations) {
      op.execute();
    }
  }
}
