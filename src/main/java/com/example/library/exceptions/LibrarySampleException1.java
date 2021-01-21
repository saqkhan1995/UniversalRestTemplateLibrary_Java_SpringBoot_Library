package com.example.library.exceptions;

public class LibrarySampleException1 extends RuntimeException{
    public LibrarySampleException1() {
    }

    public LibrarySampleException1(String message) {
        super(message);
    }

    public LibrarySampleException1(String message, Throwable cause) {
        super(message, cause);
    }

    public LibrarySampleException1(Throwable cause) {
        super(cause);
    }
}
