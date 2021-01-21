package com.example.library.exceptions;

public class LibrarySampleException2 extends RuntimeException{
    public LibrarySampleException2() {
    }

    public LibrarySampleException2(String message) {
        super(message);
    }

    public LibrarySampleException2(String message, Throwable cause) {
        super(message, cause);
    }

    public LibrarySampleException2(Throwable cause) {
        super(cause);
    }
}
