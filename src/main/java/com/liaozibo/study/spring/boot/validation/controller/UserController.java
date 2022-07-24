package com.liaozibo.study.spring.boot.validation.controller;

import com.liaozibo.study.spring.boot.validation.dao.UserRepository;
import com.liaozibo.study.spring.boot.validation.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    ResponseEntity<String> addUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok("User is valid");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                error -> {
                    String fileName = ((FieldError)error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fileName, errorMessage);
                }
        );
        return errors;
    }
}
