package com.r2d2.financeaccount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TxnBadRequestException extends ApiException {
    public TxnBadRequestException() {}
    public TxnBadRequestException(String s) {
        super(s);
    }
}
