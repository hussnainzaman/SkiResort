package org.example.test;

import org.example.schema.SkierData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SkierDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String expectedResortID = "123";
        String expectedSeasonID = "2022";
        String expectedDayID = "7";
        int expectedSkierID = 456;

        // Act
        SkierData skierData = new SkierData(expectedResortID, expectedSeasonID, expectedDayID, expectedSkierID);

        // Assert
        Assertions.assertEquals(expectedResortID, skierData.getResortID());
        Assertions.assertEquals(expectedSeasonID, skierData.getSeasonID());
        Assertions.assertEquals(expectedDayID, skierData.getDayID());
        Assertions.assertEquals(expectedSkierID, skierData.getSkierID());
    }

    @Test
    void testToString() {
        // Arrange
        String resortID = "456";
        String seasonID = "2023";
        String dayID = "10";
        int skierID = 789;
        String expectedString = "SkierData [resortID=" + resortID + ", seasonID=" + seasonID + ", dayID=" + dayID + ", skierID=" + skierID + "]";
        SkierData skierData = new SkierData(resortID, seasonID, dayID, skierID);

        // Act
        String actualString = skierData.toString();

        // Assert
        Assertions.assertEquals(expectedString, actualString);
    }

    @Test
    void testSetters() {
        // Arrange
        SkierData skierData = new SkierData("", "", "", 0);
        String expectedResortID = "789";
        String expectedSeasonID = "2024";
        String expectedDayID = "15";
        int expectedSkierID = 999;

        // Act
        skierData.setResortID(expectedResortID);
        skierData.setSeasonID(expectedSeasonID);
        skierData.setDayID(expectedDayID);
        skierData.setSkierID(expectedSkierID);

        // Assert
        Assertions.assertEquals(expectedResortID, skierData.getResortID());
        Assertions.assertEquals(expectedSeasonID, skierData.getSeasonID());
        Assertions.assertEquals(expectedDayID, skierData.getDayID());
        Assertions.assertEquals(expectedSkierID, skierData.getSkierID());
    }
}
