package com.berk.dragons.patterns;

import java.time.LocalDateTime;

public class SystemLogger {
    private static SystemLogger instance;

    private SystemLogger() {}

    public static synchronized SystemLogger getInstance() {
        if (instance == null) {
            instance = new SystemLogger();
        }
        return instance;
    }

    public void info(String message) {
        System.out.println("[INFO " + LocalDateTime.now() + "]: " + message);
    }

    public void error(String message) {
        System.err.println("[ERROR " + LocalDateTime.now() + "]: " + message);
    }
}