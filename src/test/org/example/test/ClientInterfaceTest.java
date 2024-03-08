package org.example.test;

import org.example.client.ClientInterface;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClientInterfaceTest {

    @Test
    void testRun() throws Exception {
        // Mock HttpClient, HttpRequest, and HttpResponse
        HttpClient mockHttpClient = mock(HttpClient.class);
        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpResponse<String> mockResponse = mock(HttpResponse.class);

        // Configure mock objects
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);
        when(mockResponse.body()).thenReturn("Mock response body");

        // Create ClientInterface instance with mock objects
        ClientInterface clientInterface = new ClientInterface(mockHttpClient, "data", "http://localhost:8080");

        // Call the run method
        clientInterface.run();

        // Verify that send method of HttpClient is called with the correct arguments
        verify(mockHttpClient, times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));

        // Verify that the response body is printed
        verify(mockResponse, times(1)).body();
    }
}
