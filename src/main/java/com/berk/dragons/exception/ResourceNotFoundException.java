package com.berk.dragons.exception;

public class ResourceNotFoundException extends DragonAppException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}