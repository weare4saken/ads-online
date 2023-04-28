package ru.skypro.project.marketplace.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.skypro.project.marketplace.exception.BadCredentialsException;

@ControllerAdvice
public class BadCridentialsControllerAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentials() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
