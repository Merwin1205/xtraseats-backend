package com.xtraseats.exception;

public class NotSubscribedException extends RuntimeException {
    public NotSubscribedException(Long userId) {
        super("User " + userId + " is not subscribed. Pay Rs.30 to unlock contacts.");
    }
}