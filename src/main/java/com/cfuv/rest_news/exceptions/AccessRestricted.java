package com.cfuv.rest_news.exceptions;

public class AccessRestricted extends RuntimeException {
    public AccessRestricted(String message) {
        super(message);
    }
}
