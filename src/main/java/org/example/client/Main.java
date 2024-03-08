package org.example.client;

public class Main {

    public static void main(String[] args) {
        // Instantiate your client class
        LiftRideClient client = new LiftRideClient();

        // Configure client parameters (if needed)
        client.setThreads(32);  // Set the number of threads

        // Start the client
        client.start();
    }
}
