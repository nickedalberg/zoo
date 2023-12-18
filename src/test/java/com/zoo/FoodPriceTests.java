package com.zoo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FoodPriceTests {
    @Autowired
    FoodPrice foodPrice;

    @Test
    public void readAllLinesFromPricesShouldEqual() {
        List<String> readAllLinesFromPrices = Arrays.asList("Meat=12.56", "Fruit=5.60");

        assertEquals(readAllLinesFromPrices, foodPrice.readAllLinesFromPrices());
    }
}
