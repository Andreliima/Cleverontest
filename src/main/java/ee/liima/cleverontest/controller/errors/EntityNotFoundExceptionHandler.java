package ee.liima.cleverontest.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class EntityNotFoundExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public final Object handleEntityNotFoundException(EntityNotFoundException enfe, HandlerMethod handlerMethod) {
        return new ResponseEntity<>(enfe.getMessage(), HttpStatus.NOT_FOUND);
    }

}
