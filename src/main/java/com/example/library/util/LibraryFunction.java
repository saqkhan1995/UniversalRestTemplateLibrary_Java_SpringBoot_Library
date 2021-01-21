package com.example.library.util;

import org.springframework.http.HttpMethod;

public enum LibraryFunction {
    DO_SOMETHING1("/path/something1", HttpMethod.POST),
    DO_SOMETHING2("/path/something2", HttpMethod.DELETE),
    DO_SOMETHING3("/path/something3", HttpMethod.GET);

    private final String path;
    private final HttpMethod method;

    LibraryFunction(String path, HttpMethod method) {
        this.path = path;
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public HttpMethod getMethod() {
        return method;
    }
}
