package ee.liima.cleverontest.controller.errors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class EmptyResultDataAccessExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public final Object handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, HandlerMethod handlerMethod) {
        return new ResponseEntity<>("Entity not found!", HttpStatus.NOT_FOUND);
    }

}
