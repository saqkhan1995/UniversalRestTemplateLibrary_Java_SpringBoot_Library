package com.example.library.service;

import com.example.library.exceptions.LibrarySampleException1;
import com.example.library.exceptions.LibrarySampleException2;

public interface LibraryService {

    String operation(final String id, final String name, final String JWTToken) throws LibrarySampleException1, LibrarySampleException2;

    void operation2(final String id, final String name, final String JWTToken) throws LibrarySampleException1, LibrarySampleException2;
}
