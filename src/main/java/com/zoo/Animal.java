package com.zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

@Component
public class Animal {
    private String name;
    private String animalType;
    private double weight;

    @Autowired
    private Food food;

    public String getWeightFromXml(String name, String animalType) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("src\\main\\resources\\zoo.xml"));
            document.getDocumentElement().normalize();

            NodeList animalList = document.getElementsByTagName(animalType);
            NodeList animalListChild = animalList.item(0).getChildNodes();

            for (int i = 0 ; i < animalListChild.getLength() ; i++) {
                Node detail = animalListChild.item(i);
                if(detail.getNodeType() == Node.ELEMENT_NODE) {
                    Element detailElement = (Element) detail;
                    if (((Element) detail).getAttribute("name").equals(name)){
                        return detailElement.getTagName() + "," + (detailElement.getAttribute("kg"));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading xml: " + e);
        }
        throw new IllegalArgumentException("The specific animal cannot be found");
    }

    public void setAll(String name, String animalType) {

        this.name = name;
        String xmlString = getWeightFromXml(name, animalType);
        String[] splitXmlString = xmlString.split(",");

        this.animalType = splitXmlString[0];
        this.weight = Double.parseDouble(splitXmlString[1]);
        food.setAll(this.animalType, this.weight);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
