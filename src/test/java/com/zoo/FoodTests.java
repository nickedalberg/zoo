package com.zoo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FoodTests {
    @Autowired
    Food food;

    @Test
    public void lionShouldEqualPointOneAndOneHundred() {
        String animalType = "Lion";
        List<String> animalInfoString = Arrays.asList("0.10", "100");

        assertEquals(animalInfoString, food.getAnimalInfoString(animalType));
    }

    @Test
    public void tigerShouldEqualPointZeroNineAndOneHundred() {
        String animalType = "Tiger";
        List<String> animalInfoString = Arrays.asList("0.09", "100");

        assertEquals(animalInfoString, food.getAnimalInfoString(animalType));
    }

    @Test
    public void giraffeShouldEqualPointZeroEightAndZero() {
        String animalType = "Giraffe";
        List<String> animalInfoString = Arrays.asList("0.08", "0");

        assertEquals(animalInfoString, food.getAnimalInfoString(animalType));
    }

    @Test
    public void zebraShouldEqualPointZeroNineAndOneHundred() {
        String animalType = "Zebra";
        List<String> animalInfoString = Arrays.asList("0.08", "0");

        assertEquals(animalInfoString, food.getAnimalInfoString(animalType));
    }

    @Test
    public void wolfShouldEqualPointZeroSevenAndNinety() {
        String animalType = "Wolf";
        List<String> animalInfoString = Arrays.asList("0.07", "90");

        assertEquals(animalInfoString, food.getAnimalInfoString(animalType));
    }

    @Test
    public void piranhaShouldEqualPointZeroFiveAndFifty() {
        String animalType = "Piranha";
        List<String> animalInfoString = Arrays.asList("0.5", "50");

        assertEquals(animalInfoString, food.getAnimalInfoString(animalType));
    }

    @Test
    public void wrongAnimalTypeShouldEqualPointZeroFiveAndFifty() {
        String animalType = "wronganimaltype";
        List<String> animalInfoString = Arrays.asList("0.5", "50");

        assertThrows(IllegalArgumentException.class,
                () -> {
                    food.getAnimalInfoString(animalType);
                });
    }
}
