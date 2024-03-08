package org.example.test;

import org.example.client.ClientUploader;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientUploaderTest {

    @Test
    void testRun() throws Exception {
        // Mock HttpClient and HttpResponse
        HttpClient mockHttpClient = mock(HttpClient.class);
        HttpResponse<String> mockResponse = mock(HttpResponse.class);

        // Configure mock objects
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);
        when(mockResponse.statusCode()).thenReturn(201);

        // Create ClientUploader instance with mock HttpClient
        String endpoint = "http://example.com";
        ClientUploader clientUploader = new ClientUploader(endpoint);
        //clientUploader.client = mockHttpClient;

        // Call the run method
       // clientUploader.run();

        // Verify that send method of HttpClient is called with the correct arguments
        //Mockito.verify(mockHttpClient, Mockito.atLeastOnce()).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));

        // You can add more assertions based on the expected behavior of the ClientUploader class
    }
}
