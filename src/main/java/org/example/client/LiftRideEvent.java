package org.example.client;

import java.util.Random;

public class LiftRideEvent {
    private int skierID;
    private int resortID;
    private int liftID;
    private int seasonID;
    private int dayID;
    private int time;

    // Constructor
    public LiftRideEvent(int skierID, int resortID, int liftID, int seasonID, int dayID, int time) {
        this.skierID = skierID;
        this.resortID = resortID;
        this.liftID = liftID;
        this.seasonID = seasonID;
        this.dayID = dayID;
        this.time = time;
    }

    // Generate a random LiftRideEvent
    public static LiftRideEvent generateRandomEvent() {
        Random random = new Random();
        int skierID = random.nextInt(100000) + 1;
        int resortID = random.nextInt(10) + 1;
        int liftID = random.nextInt(40) + 1;
        int seasonID = 2024;
        int dayID = 1;
        int time = random.nextInt(360) + 1;

        return new LiftRideEvent(skierID, resortID, liftID, seasonID, dayID, time);
    }

    // Getters and setters
    // Add getters and setters for all fields
}
