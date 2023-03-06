package com.niit.bej.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Customer is already exist in database")
public class CustomerAlreadyExistException extends Exception {
    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
