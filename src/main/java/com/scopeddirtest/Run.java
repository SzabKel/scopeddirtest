package com.scopeddirtest;

import java.util.concurrent.Executors;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting scoped dir test");
        int cores = Runtime.getRuntime().availableProcessors();

        BrowserStarter browserStarter = new BrowserStarter("http://localhost:4444/wd/hub");

        BrowserStarterExecutor executor = new BrowserStarterExecutor( Executors.newFixedThreadPool(cores));

        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> {
                browserStarter.startAndClose();
            });
        }

        executor.waitForCompletion();

        System.out.println("Done");
    }
}
