package com.jpabook.jpashop.exception;

public class DuplicateIdException extends RuntimeException {

    public DuplicateIdException() {
        super();
    }

    public DuplicateIdException(String message) {
        super(message);
    }

    public DuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateIdException(Throwable cause) {
        super(cause);
    }
}
