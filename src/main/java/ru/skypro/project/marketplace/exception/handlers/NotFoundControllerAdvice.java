package ru.skypro.project.marketplace.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.skypro.project.marketplace.exception.AdsNotFoundException;
import ru.skypro.project.marketplace.exception.CommentNotFoundException;
import ru.skypro.project.marketplace.exception.ImageNotFoundException;
import ru.skypro.project.marketplace.exception.UsernameNotFoundException;

@ControllerAdvice
public class NotFoundControllerAdvice {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> notFoundUsername(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(AdsNotFoundException.class)
    public ResponseEntity<?> notFoundAds(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<?> notFoundImage(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> notFoundComment() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
