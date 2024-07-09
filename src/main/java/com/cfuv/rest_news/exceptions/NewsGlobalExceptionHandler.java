package com.cfuv.rest_news.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NewsGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<NewsIncorrectData> handleException(NoSuchNewsException exception) {
        NewsIncorrectData data = new NewsIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<NewsIncorrectData> handleException(Exception exception) {
        NewsIncorrectData data = new NewsIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public ResponseEntity<NewsIncorrectData> handleException(AccessRestricted exception) {
        NewsIncorrectData data = new NewsIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler
    public ResponseEntity<NewsIncorrectData> handleException(AccessDeniedException exception) {
        NewsIncorrectData data = new NewsIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.FORBIDDEN);

    }
}