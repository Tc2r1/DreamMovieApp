package com.tc2r.dreammovieapp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * null.java
 * Dream App Developments
 * <p>
 * Created by Nudennie White aka Tc2r on 1/12/23.
 * Copyright © 2023. All rights reserved.
 */
public class AppExecutors {
    // Singleton Pattern
    private static AppExecutors instance;

    public static AppExecutors getInstance() {
        if(instance == null) {
            instance = new AppExecutors();
        }
        return instance;
    }

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO() {
        return scheduledExecutorService;
    }
}
