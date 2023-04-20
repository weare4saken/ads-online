package ru.skypro.project.marketplace.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.skypro.project.marketplace.exception.IncorrectUsernameException;

public class BadRequestControllerAdvice {

    @ExceptionHandler(IncorrectUsernameException.class)
    public ResponseEntity<?> incorrectUsername() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
