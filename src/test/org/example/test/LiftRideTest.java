package org.example.test;

import org.example.schema.LiftRide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LiftRideTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        int expectedLiftID = 5;
        int expectedTime = 120;

        // Act
        LiftRide liftRide = new LiftRide(expectedLiftID, expectedTime);

        // Assert
        Assertions.assertEquals(expectedLiftID, liftRide.getLiftID());
        Assertions.assertEquals(expectedTime, liftRide.getTime());
    }

    @Test
    void testToString() {
        // Arrange
        int liftID = 3;
        int time = 90;
        String expectedString = "LiftRide [time=" + time + ", liftID=" + liftID + "]";
        LiftRide liftRide = new LiftRide(liftID, time);

        // Act
        String actualString = liftRide.toString();

        // Assert
        Assertions.assertEquals(expectedString, actualString);
    }

    @Test
    void testSetters() {
        // Arrange
        LiftRide liftRide = new LiftRide();
        int expectedLiftID = 10;
        int expectedTime = 180;

        // Act
        liftRide.setLiftID(expectedLiftID);
        liftRide.setTime(expectedTime);

        // Assert
        Assertions.assertEquals(expectedLiftID, liftRide.getLiftID());
        Assertions.assertEquals(expectedTime, liftRide.getTime());
    }
}
