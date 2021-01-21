package com.example.library.client;

import com.example.library.configuration.LibraryValuesConfig;
import com.example.library.configuration.LibraryValuesSecurityConfig;
import com.example.library.exceptions.LibrarySampleException1;
import com.example.library.exceptions.LibrarySampleException2;
import com.example.library.model.RequestSample;
import com.example.library.model.ResponseSample;
import com.example.library.util.LibraryFunction;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static com.example.library.util.LibraryFunction.DO_SOMETHING1;
import static com.example.library.util.LibraryFunction.DO_SOMETHING2;

public class LibraryClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryClient.class);

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private LibraryValuesConfig config;
    private LibraryValuesSecurityConfig securityConfig;

    public LibraryClient(RestTemplate restTemplate, ObjectMapper objectMapper, LibraryValuesConfig config, LibraryValuesSecurityConfig securityConfig) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.config = config;
        this.securityConfig = securityConfig;
    }

    public ResponseSample getDetailsFromApi(RequestSample request, String JWT)
            throws LibrarySampleException1 {
        try {
            HttpEntity<RequestSample> requestEntity = new HttpEntity<>(request, constructionHeaders(JWT));
            ResponseEntity<ResponseSample> responseEntity = handleExchange(requestEntity, DO_SOMETHING1, ResponseSample.class);
            return responseEntity.getBody();
        } catch (LibrarySampleException1 | LibrarySampleException2 ex) {
            throw new LibrarySampleException1(ex.getMessage());
        }
    }

    public void getDetailsFromApi2_DELETE(RequestSample request, String JWT)
            throws LibrarySampleException2 {
        try {
            HttpEntity<RequestSample> requestEntity = new HttpEntity<>(request, constructionHeaders(JWT));
            handleExchange(requestEntity, DO_SOMETHING2, Void.class);
        } catch (LibrarySampleException1 | LibrarySampleException2 ex) {
            throw new LibrarySampleException1(ex.getMessage());
        }
    }

    private <T>ResponseEntity<T> handleExchange(HttpEntity request, LibraryFunction function, Class<T> responseClass, Object... params)
        throws LibrarySampleException2, LibrarySampleException1 {
        String url = config.getUrl().concat(function.getPath());
        ResponseEntity<T> response = null;
        try {
            response = restTemplate.exchange(url, function.getMethod(), request, responseClass, params);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            LOGGER.info("handle error here accordingly :{}, {}", ex.getStatusCode(), ex.getResponseBodyAsString());
            handleErrors(function, ex.getStatusCode(), ex.getResponseBodyAsString());
        }
        return response;

    }

    private HttpHeaders constructionHeaders(String JWT) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.ACCEPT, String.valueOf(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.AUTHORIZATION, JWT);
        headers.set("ClientID", securityConfig.getClientId());
        headers.set("ClientSecret", securityConfig.getClientSecret());

        return headers;
    }

    public String getErrorMessage(String errorBody) {
        try {
            JsonNode root = objectMapper.readTree(errorBody);
            return root.at("/errors/0/message").asText();
        } catch(Exception e) {
            return "";
        }
    }

    private void handleErrors(LibraryFunction function, HttpStatus exStatus, String exResponseBody)
        throws LibrarySampleException1, LibrarySampleException2 {
        switch (function) {
            case DO_SOMETHING1:
                if(exStatus.equals(HttpStatus.BAD_REQUEST)) {
                    throw new LibrarySampleException1(getErrorMessage(exResponseBody));
                }
            case DO_SOMETHING2:
                if(exStatus.equals(HttpStatus.BAD_REQUEST)) {
                    throw new LibrarySampleException2(getErrorMessage(exResponseBody));
                }
            default:
                throw new LibrarySampleException2(getErrorMessage(exResponseBody));
        }
    }

}
