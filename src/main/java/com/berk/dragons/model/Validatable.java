package com.berk.dragons.model;

import com.berk.dragons.exception.InvalidInputException;

public interface Validatable {
    void validate() throws InvalidInputException;

    default void logValidation() {
        System.out.println("[AUDIT]: Validating object state...");
    }

    static void checkString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }
}