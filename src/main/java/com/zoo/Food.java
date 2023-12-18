package com.zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Food {
    private double foodRate;
    private double foodPercentage;
    private double foodAmount;
    @Autowired
    private FoodPrice foodPrice;

    public List<String> getAnimalInfoString(String animalType) {
        try {
            ClassPathResource resource = new ClassPathResource("animals.csv");
            Path filePath = Paths.get(resource.getURI());

            String animalInfoString = Files.lines(filePath).filter(line -> line.contains(animalType))
                    .collect(Collectors.joining());

            List<String> splitStringList = new ArrayList<>(Arrays.asList(animalInfoString.split(";")));

            splitStringList.remove(0);

            if (splitStringList.size() == 2) {
                return updateValuesForSizeTwo(splitStringList);
            }

            else if (splitStringList.size() == 3) {
                return updateValuesForSizeThree(splitStringList);
            }
        }
        catch (Exception e) {
            System.out.println("Error reading from animals.csv: " + e);
        }

        throw new IllegalArgumentException("The matching food cannot be found");
    }

    private List<String> updateValuesForSizeTwo(List<String> splitStringList) {
        if (splitStringList.get(1).equals("meat")) {
            splitStringList.set(1, "100");
        }
        else if (splitStringList.get(1).equals("fruit")) {
            splitStringList.set(1, "0");
        }

        return splitStringList;
    }

    private List<String> updateValuesForSizeThree(List<String> splitStringList) {
        String temp = splitStringList.get(2).substring(0, splitStringList.get(2).length() - 1);

        splitStringList.remove(1);
        splitStringList.remove(1);
        splitStringList.add(temp);

        return splitStringList;
    }

    public void setAll(String animalType, double weight) {

        List<String> animalInfoList = getAnimalInfoString(animalType);
        this.foodRate = Double.parseDouble(animalInfoList.get(0));
        this.foodPercentage = Double.parseDouble(animalInfoList.get(1))/100;

        this.foodAmount = weight * foodRate;

        foodPrice.setAll(this.foodPercentage, this.foodAmount);
    }
    public double getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(double foodRate) {
        this.foodRate = foodRate;
    }

    public double getFoodPercentage() {
        return foodPercentage;
    }

    public void setFoodPercentage(double foodPercentage) {
        this.foodPercentage = foodPercentage;
    }

    public double getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(double foodAmount) {
        this.foodAmount = foodAmount;
    }

    public FoodPrice getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(FoodPrice foodPrice) {
        this.foodPrice = foodPrice;
    }
}
