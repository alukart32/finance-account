package com.r2d2.financeaccount.exception;

public class UniqueViolationException extends ApiException {
    public UniqueViolationException() {
        super();
    }

    public UniqueViolationException(String s) {
        super(s);
    }
}
