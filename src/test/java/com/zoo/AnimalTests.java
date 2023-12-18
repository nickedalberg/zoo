package com.zoo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AnimalTests {
    @Autowired
    private Animal animal;

    @Test
    public void lionShouldEqualLion160() {
        String name = "Simba";
        String animalType = "Lions";
        String xmlString = "Lion,160";

        assertEquals(xmlString, animal.getWeightFromXml(name, animalType));
    }

    @Test
    public void annaGiraffesShouldEqualGiraffe202() {
        String name = "Anna";
        String animalType = "Giraffes";
        String xmlString = "Giraffe,202";

        assertEquals(xmlString, animal.getWeightFromXml(name, animalType));
    }

    @Test
    public void tolkienTigersShouldEqualTiger139() {
        String name = "Tolkien";
        String animalType = "Tigers";
        String xmlString = "Tiger,139";

        assertEquals(xmlString, animal.getWeightFromXml(name, animalType));
    }

    @Test
    public void chipZebrasShouldEqualZebra100() {
        String name = "Chip";
        String animalType = "Zebras";
        String xmlString = "Zebra,100";

        assertEquals(xmlString, animal.getWeightFromXml(name, animalType));
    }

    @Test
    public void ponWolvesShouldEqualWolf69() {
        String name = "Pon";
        String animalType = "Wolves";
        String xmlString = "Wolf,69";

        assertEquals(xmlString, animal.getWeightFromXml(name, animalType));
    }

    @Test
    public void anastasiaPiranhasShouldEqualPiranha05() {
        String name = "Anastasia";
        String animalType = "Piranhas";
        String xmlString = "Piranha,0.5";

        assertEquals(xmlString, animal.getWeightFromXml(name, animalType));
    }

    @Test
    public void nalaZebrasShouldEqualIllegalArgumentException() {
        String name = "Nala";
        String animalType = "Zebras";

        assertThrows(IllegalArgumentException.class,
                () -> {
                    animal.getWeightFromXml(name, animalType);
                });
    }

    @Test
    public void wrongNameTigersShouldEqualIllegalArgumentException() {
        String name = "wrongname";
        String animalType = "Tigers";

        assertThrows(IllegalArgumentException.class,
                () -> {
                    animal.getWeightFromXml(name, animalType);
                });
    }

    @Test
    public void susannaWrongAnimalTypeShouldEqualIllegalArgumentException() {
        String name = "Susanna";
        String animalType = "wronganimaltype";

        assertThrows(IllegalArgumentException.class,
                () -> {
                    animal.getWeightFromXml(name, animalType);
                });
    }

    @Test
    public void wrongNameWrongAnimalTypeShouldEqualIllegalArgumentException() {
        String name = "wrongname";
        String animalType = "wronganimaltype";

        assertThrows(IllegalArgumentException.class,
                () -> {
                    animal.getWeightFromXml(name, animalType);
                });
    }

}

