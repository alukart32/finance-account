package com.r2d2.financeaccount.exception;

public class ForbiddenException extends ApiException {
    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String s) {
        super(s);
    }
}
