package com.berk.dragons.utils;

import com.berk.dragons.patterns.SystemLogger;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionUtils {
    public static void inspectClass(Object obj) {
        Class<?> clazz = obj.getClass();
        String message = "Inspecting class: " + clazz.getSimpleName();
        SystemLogger.getInstance().info(message);

        System.out.println("--- Methods of " + clazz.getSimpleName() + " ---");
        Arrays.stream(clazz.getDeclaredMethods())
                .map(Method::getName)
                .forEach(System.out::println);
    }
}