package com.scopeddirtest;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class BrowserStarterExecutor {
    private final ExecutorService executorService;
    private final Queue<Throwable> throwables = new ConcurrentLinkedQueue<>();

    public BrowserStarterExecutor(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void execute(Runnable runnable) {
        executorService.execute(()->{
            try {
                runnable.run();
            } catch (Throwable t) {
                throwables.add(t);
            }
        });
    }

    public void waitForCompletion() throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);

        if(throwables.size() > 0) {
            System.err.println("We had at least one exception during Execution! Rethrowing...");
            throw new RuntimeException(throwables.poll());
        }
    }
}
