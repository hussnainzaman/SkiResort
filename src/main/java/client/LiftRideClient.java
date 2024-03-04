package client;

import java.sql.SQLOutput;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LiftRideClient {
    private int threads;
    private static final int TOTAL_REQUESTS = 10000; // Total number of requests to be sent
    private static final String SERVER_URL = "http://your-server-url.com"; // Replace with your server URL

    // Constructor
    public LiftRideClient() {
        // Initialize any necessary configurations
    }

    // Method to start the client
    public void start() {
        // Create a fixed thread pool with the specified number of threads
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        // Generate and send lift ride events in separate threads
        for (int i = 0; i < threads; i++) {
            executorService.execute(new LiftRideEventSender(i));
        }

        // Shutdown the executor service after all tasks are completed
        executorService.shutdown();

        try {
            // Wait for all threads to terminate
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All requests sent successfully.");
    }

    // Method to set the number of threads
    public void setThreads(int threads) {
        this.threads = threads;
    }

    // Inner class to handle sending lift ride events
    private class LiftRideEventSender implements Runnable {
        private final int threadId;

        public LiftRideEventSender(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            for (int i = 0; i < TOTAL_REQUESTS / threads; i++) {
                // Generate a random lift ride event
                LiftRideEvent event = generateRandomEvent();
                //System.out.println(event);

                // Send the event to the server
                boolean success = sendEventToServer(event);

                // Retry if unsuccessful
                int retries = 0;
                while (!success && retries < 5) {
                    retries++;
                    System.out.println("Retrying request for thread " + threadId + ", retry #" + retries);
                    success = sendEventToServer(event);
                }
            }
        }

        // Method to generate a random lift ride event
        private LiftRideEvent generateRandomEvent() {
            // Generate random values for skierID, resortID, liftID, seasonID, dayID, and time
            int skierID = (int) (Math.random() * 100000) + 1;
            int resortID = (int) (Math.random() * 10) + 1;
            int liftID = (int) (Math.random() * 40) + 1;
            int seasonID = 2022;
            int dayID = 1;
            int time = (int) (Math.random() * 360) + 1;
            System.out.println(skierID +","+ resortID+","+liftID+","+","+seasonID+","+dayID+","+time);

            return new LiftRideEvent(skierID, resortID, liftID, seasonID, dayID, time);
        }

        // Method to send lift ride event to the server
        private boolean sendEventToServer(LiftRideEvent event) {
            // Implement HTTP POST request to send lift ride event to the server
           // System.out.println("Server Request sent Sucessfully");
            // Return true if successful, false otherwise (simulate failures for testing)
            return true; // Placeholder, replace with actual implementation
        }
    }
}
