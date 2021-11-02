package com.library.mgmt.exception;

public class DuplicateBorrowingBookException extends RuntimeException {

    public DuplicateBorrowingBookException(String msg) {
        super(msg);
    }
}
