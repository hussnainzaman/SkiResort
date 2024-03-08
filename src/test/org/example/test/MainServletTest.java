package org.example.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.schema.LiftRide;
import org.example.servlet.MainServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;

import static org.mockito.Mockito.when;

class MainServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoPostWithValidInput() throws ServletException, IOException {
        // Mocking the request
        when(request.getPathInfo()).thenReturn("/1/seasons/2022/days/1/skiers/1234");

        // Creating a dummy LiftRide object
        LiftRide liftRide = new LiftRide(5, 30);

        // Creating a JSON representation of the LiftRide object
        String jsonRequest = "{\"liftID\":5,\"time\":30}";

        // Setting up the request input stream
        BufferedReader reader = new BufferedReader(new StringReader(jsonRequest));
        when(request.getReader()).thenReturn(reader);

        // Mocking the response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Calling the doPost method
        MainServlet servlet = new MainServlet();
        servlet.doPost(request, response);

        // Verifying the response
        writer.flush();
        String expectedResponse = "Event Created";
        String actualResponse = stringWriter.toString().trim();
        assert actualResponse.equals(expectedResponse) : "Response mismatch";
    }

    @Test
    void testDoPostWithInvalidInput() throws ServletException, IOException {
        // Mocking the request
        when(request.getPathInfo()).thenReturn("/1/seasons/2022/days/1/skiers/1234");

        // Creating an invalid JSON request
        String jsonRequest = "{\"liftID\":50,\"time\":400}";

        // Setting up the request input stream
        BufferedReader reader = new BufferedReader(new StringReader(jsonRequest));
        when(request.getReader()).thenReturn(reader);

        // Mocking the response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Calling the doPost method
        MainServlet servlet = new MainServlet();
        servlet.doPost(request, response);

        // Verifying the response
        writer.flush();
        String expectedResponse = "{\"message\":\"Invalid input: liftID should be in range [1,40] and time should be in range [1,360]\"}";
        String actualResponse = stringWriter.toString().trim();
        assert actualResponse.equals(expectedResponse) : "Response mismatch";
    }
}
