package org.example.test;

import org.example.schema.SkierLiftRideEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SkierLiftRideEventTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        int expectedSkierId = 1234;
        int expectedResortId = 5;
        int expectedLiftId = 10;
        int expectedSeasonId = 2022;
        int expectedDayId = 7;
        int expectedTime = 120;

        // Act
        SkierLiftRideEvent event = new SkierLiftRideEvent(expectedSkierId, expectedResortId, expectedLiftId,
                expectedSeasonId, expectedDayId, expectedTime);

        // Assert
        Assertions.assertEquals(expectedSkierId, event.getSkierId());
        Assertions.assertEquals(expectedResortId, event.getResortId());
        Assertions.assertEquals(expectedLiftId, event.getLiftId());
        Assertions.assertEquals(expectedSeasonId, event.getSeasonId());
        Assertions.assertEquals(expectedDayId, event.getDayId());
        Assertions.assertEquals(expectedTime, event.getTime());
    }
}
