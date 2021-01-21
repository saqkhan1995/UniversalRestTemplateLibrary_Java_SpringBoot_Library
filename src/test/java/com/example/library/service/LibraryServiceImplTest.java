package com.example.library.service;

import com.example.library.client.LibraryClient;
import com.example.library.exceptions.LibrarySampleException1;
import com.example.library.model.RequestSample;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.example.library.testutil.TestConstants.*;
import static com.example.library.util.LibraryConstants.ERROR_CONSTANT_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LibraryServiceImplTest {

    @Mock
    private LibraryClient client;

    @InjectMocks
    private LibraryServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_ServiceHappyPath() throws LibrarySampleException1 {
        when(client.getDetailsFromApi(any(RequestSample.class), anyString())).thenReturn(SAMPLE_RESPONSE);

        final String something = service.operation(ID, NAME, JWT);
        assertEquals(SOMETHING, something);
    }

    @Test
    public void test_Service_UnHappyPath() throws LibrarySampleException1 {
        when(client.getDetailsFromApi(any(RequestSample.class), anyString())).thenThrow(new LibrarySampleException1(SERVICE_EXCEPTION1));

        try{
            final String something = service.operation(ID, NAME, JWT);
        } catch (LibrarySampleException1 ex) {
            assertEquals(SERVICE_EXCEPTION1, ex.getMessage());
        }
    }

    @Test
    public void test_DeleteOps_HappyPath() throws LibrarySampleException1 {
        doNothing().when(client).getDetailsFromApi2_DELETE(any(RequestSample.class), anyString());
        service.operation2(ID, NAME, JWT);
        verify(client, times(1)).getDetailsFromApi2_DELETE(any(RequestSample.class), anyString());
    }

    @Test
    public void test_DeleteOps_UnHappyPath() throws LibrarySampleException1 {
        doThrow(new LibrarySampleException1(SERVICE_EXCEPTION1)).when(client)
                .getDetailsFromApi2_DELETE(any(RequestSample.class), anyString());

        try {
            service.operation2(ID, NAME, JWT);
        } catch (LibrarySampleException1 ex) {
            assertEquals(SERVICE_EXCEPTION1, ex.getMessage());
        }
    }


}
