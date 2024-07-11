package com.cfuv.rest_news.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NewsGlobalExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler
    public ResponseEntity<NewsIncorrectData> handleException(NoSuchNewsException exception) {
        NewsIncorrectData data = new NewsIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<NewsIncorrectData> handleException(Exception exception) {
        NewsIncorrectData data = new NewsIncorrectData();
        data.setInfo("Произошла внутренняя ошибка");
        LOGGER.error(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);

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
