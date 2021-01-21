package com.example.library.testutil;

import com.example.library.model.RequestSample;
import com.example.library.model.ResponseSample;

public class TestConstants {

    //Values
    public static final String URL = "TEST_URL";
    public static final String CLIENT_ID = "TEST_CLIENT_ID";
    public static final String CLIENT_SECRET = "TEST_CLIENT_SECRET";

    public static final String ID = "TEST_ID";
    public static final String NAME = "TEST_NAME";
    public static final String SOMETHING = "TEST_SOMETHING";

    public static final String JWT = "TEST_SOMETHING";

    //Request-Response Objects
    public static final ResponseSample SAMPLE_RESPONSE = new ResponseSample(ID, NAME, SOMETHING);
    public static final RequestSample SAMPLE_REQUEST = new RequestSample(ID, NAME, SOMETHING);

    //Error Constants
    public static final String SERVICE_EXCEPTION1 = "{\"errors\":[{\"message\":\"service_exception\"}]}";
    public static final String SERVICE_EXCEPTION2 = "{\"errors\":[{\"message\":\"service_exception\"}]}";
}
