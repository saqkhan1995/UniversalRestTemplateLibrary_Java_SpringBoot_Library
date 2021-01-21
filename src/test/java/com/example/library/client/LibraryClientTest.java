package com.example.library.client;

import com.example.library.configuration.LibraryValuesConfig;
import com.example.library.configuration.LibraryValuesSecurityConfig;
import com.example.library.exceptions.LibrarySampleException1;
import com.example.library.exceptions.LibrarySampleException2;
import com.example.library.model.ResponseSample;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

import static com.example.library.testutil.TestConstants.*;
import static com.example.library.util.LibraryConstants.ERROR_CONSTANT_1;
import static com.example.library.util.LibraryConstants.ERROR_CONSTANT_2;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryClientTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private LibraryValuesConfig config;

    @Mock
    private LibraryValuesSecurityConfig securityConfig;

    @Spy
    private ObjectMapper objectMapper;

    @InjectMocks
    private LibraryClient client;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(config.getUrl()).thenReturn(URL);
        when(securityConfig.getClientId()).thenReturn(CLIENT_ID);
        when(securityConfig.getClientSecret()).thenReturn(CLIENT_SECRET);
    }

    @Test
    public void testSampleHappyPath_OK() throws LibrarySampleException1 {
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class),
                ArgumentMatchers.<List<Object>>any()))
                .thenReturn(new ResponseEntity<>(SAMPLE_RESPONSE, HttpStatus.OK));

        final ResponseSample response = client.getDetailsFromApi(SAMPLE_REQUEST, JWT);
        assertEquals(SAMPLE_RESPONSE, response);
    }

    @Test
    public void testSample_UnHappyPath() throws LibrarySampleException1 {
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class),
                ArgumentMatchers.<List<Object>>any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        SERVICE_EXCEPTION1.getBytes(), Charset.defaultCharset()));

        try{
            final ResponseSample response = client.getDetailsFromApi(SAMPLE_REQUEST, JWT);
        } catch (LibrarySampleException1 ex) {
            assertEquals(ERROR_CONSTANT_1, ex.getMessage());
        }
    }

    @Test
    public void testSample_UnHappyPath_2() throws LibrarySampleException2 {
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class),
                ArgumentMatchers.<List<Object>>any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        SERVICE_EXCEPTION2.getBytes(), Charset.defaultCharset()));

        try{
            final ResponseSample response = client.getDetailsFromApi(SAMPLE_REQUEST, JWT);
        } catch (LibrarySampleException1 ex) {
            assertEquals(ERROR_CONSTANT_2, ex.getMessage());
        }
    }

    @Test
    public void testSampleHappyPath_DELETE_OK() throws LibrarySampleException1 {
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class),
                ArgumentMatchers.<List<Object>>any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        client.getDetailsFromApi2_DELETE(SAMPLE_REQUEST, JWT);
        verify(restTemplate, times(1)).exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class),
                ArgumentMatchers.<List<Object>>any());
    }




}
