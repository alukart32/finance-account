package com.r2d2.financeaccount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends ApiException {
    public NotFoundException() { }
    public NotFoundException(String s) {
        super(s);
    }
}

