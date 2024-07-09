package com.cfuv.rest_news.exceptions;

public class NoSuchNewsException extends RuntimeException {
    public NoSuchNewsException(String message) {
        super(message);
    }
}
