package com.example.starter.ping_task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Provision
{

    private static ExecutorService executor = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors() * 2);

    public static void startProvision(String ip)
    {

    }

    public static Runnable task(String ip)
    {
        return () ->
        {

        };
    }
}
