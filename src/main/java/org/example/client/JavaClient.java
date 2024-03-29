package org.example.client;

import com.google.gson.Gson;
import org.example.schema.LiftRide;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JavaClient {
    private final static Random ran = new Random();
    //public ExecutorService executor;

    public static void main(String[] args) {
       //String endpoint = "http://localhost:8080/dss/skiers/1/seasons/2022/days/7/skiers/9999";
        String endpoint = "http://68.233.127.218:8080/dss/skiers/1/seasons/2022/days/7/skiers/9999";
        // Create a thread pool and start multiple instances of the client
        int numClients = 32;
        ExecutorService executor = Executors.newFixedThreadPool(numClients);
        for (int i = 0; i < numClients; i++) {
            int liftId = ran.nextInt(40) + 1;
            int time = ran.nextInt(360) + 1;
            LiftRide liftRide = new LiftRide(liftId, time);
            Gson gson = new Gson();
            String json = gson.toJson(liftRide);

            long startTime = System.currentTimeMillis();
            executor.submit(new ClientInterface(endpoint, json));

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            System.out.println("Client " + i + " = LiftID= " + liftId + " in " + time);
            System.out.println("Elapsed Request time: " + elapsedTime + " milliseconds");
            System.out.println("Client " + i + ",LiftID= " + liftId + ",ride in " + time+ "Sec");
            System.out.println("Start Time(UMT Standard): " + startTime);
            System.out.println("Request Latency: " + elapsedTime + " milliseconds");

        }

        // Wait for all clients to finish
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

}
