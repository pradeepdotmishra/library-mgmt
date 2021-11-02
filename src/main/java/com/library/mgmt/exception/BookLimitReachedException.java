package com.library.mgmt.exception;

public class BookLimitReachedException extends RuntimeException {

    public BookLimitReachedException(String msg) {
        super(msg);
    }
}
