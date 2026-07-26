package com.github.shorturl.exception;

public class ShortCodeNotFoundException extends RuntimeException {
    public ShortCodeNotFoundException(String message) {
        super(message);
    }
}
