package com.example.library.service;

import com.example.library.client.LibraryClient;
import com.example.library.exceptions.LibrarySampleException1;
import com.example.library.exceptions.LibrarySampleException2;
import com.example.library.model.RequestSample;
import com.example.library.model.ResponseSample;

public class LibraryServiceImpl implements LibraryService{

    private final LibraryClient client;

    public LibraryServiceImpl(final LibraryClient client) {
        this.client = client;
    }

    public String operation(String id, String name, String JWTToken) throws LibrarySampleException1, LibrarySampleException2 {

        final RequestSample request = new RequestSample();
        request.setId(id);
        request.setName(name);
        request.setSomething(name);

        final ResponseSample responseSample = client.getDetailsFromApi(request, JWTToken);

        return responseSample.getSomething();
    }

    public void operation2(String id, String name, String JWTToken) throws LibrarySampleException1, LibrarySampleException2 {

        final RequestSample request = new RequestSample();
        request.setId(id);
        request.setName(name);
        request.setSomething(name);

        client.getDetailsFromApi2_DELETE(request, JWTToken);

    }
}
