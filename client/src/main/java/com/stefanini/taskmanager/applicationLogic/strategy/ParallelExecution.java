package com.stefanini.taskmanager.applicationLogic.strategy;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.stefanini.taskmanager.Operations;
import com.stefanini.taskmanager.applicationLogic.ExecutorLogic;

public class ParallelExecution implements ExecutionStrategy {
  private ExecutorService pool = Executors.newFixedThreadPool(4);

  @Override
  public void execute(List<Operations> operations) {
    ExecutorLogic executorLogic = new ExecutorLogic();
    executorLogic.sortOperation(operations);
    List<Operations> level1 = executorLogic.getLevel1();
    List<Operations> level2 = executorLogic.getLevel2();
    List<Operations> level3 = executorLogic.getLevel3();
    List<CompletableFuture<?>> level1Future =
        level1
            .stream()
            .map(op -> CompletableFuture.runAsync(op, pool))
            .collect(Collectors.toList());

    CompletableFuture<Void> combinedFuture1 =
        CompletableFuture.allOf(level1Future.toArray(new CompletableFuture[level1Future.size()]));
    List<CompletableFuture<?>> level2Future =
        level2
            .stream()
            .map(op -> combinedFuture1.thenRunAsync(op, pool))
            .collect(Collectors.toList());

    CompletableFuture<Void> combinedFuture2 =
        CompletableFuture.allOf(level2Future.toArray(new CompletableFuture[level2Future.size()]));
    List<CompletableFuture<?>> level3Future =
        level3
            .stream()
            .map(op -> combinedFuture2.thenRunAsync(op, pool))
            .collect(Collectors.toList());
    CompletableFuture<Void> combinedFuture3 =
        CompletableFuture.allOf(level3Future.toArray(new CompletableFuture[level3Future.size()]));

    CompletableFuture<Void> combinedFuturef =
        CompletableFuture.allOf(combinedFuture1, combinedFuture2, combinedFuture3);

    combinedFuturef.thenRun(() -> pool.shutdown());
  }
}
