package com.zoo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Component
public class FoodPrice {
    private double meatPrice;
    private double fruitPrice;
    private double cost;

    public List<String> readAllLinesFromPrices() {
        try {
            ClassPathResource resource = new ClassPathResource("prices.txt");
            Path filePath = Paths.get(resource.getURI());
            List<String> linesString = Files.readAllLines(filePath);
            linesString.set(0,linesString.get(0).substring(1));
            return linesString;
        } catch (Exception e) {

            System.out.println("Error reading from txt: " + e);
        }
        return Collections.emptyList();
    }

    public void setAll(double foodPercentage, double foodAmount) {

        List<String> lines = readAllLinesFromPrices();

        String[] splitString1 = lines.get(0).split("=");
        String[] splitString2 = lines.get(1).split("=");

        //Assuming first line in prices.txt always is meat
        this.meatPrice = Double.parseDouble(splitString1[1]);
        this.fruitPrice = Double.parseDouble(splitString2[1]);
        this.cost = (meatPrice * foodAmount * foodPercentage) + (fruitPrice * (1 - foodPercentage) * foodAmount);
    }

    public double getMeatPrice() {
        return meatPrice;
    }

    public void setMeatPrice(double meatPrice) {
        this.meatPrice = meatPrice;
    }

    public double getFruitPrice() {
        return fruitPrice;
    }

    public void setFruitPrice(double fruitPrice) {
        this.fruitPrice = fruitPrice;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
