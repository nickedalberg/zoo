package com.zoo;

import jakarta.xml.bind.JAXBException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.util.Scanner;

import static java.lang.Math.round;

@SpringBootApplication
public class ZooApplication {

	public static void main(String[] args) throws JAXBException {
		ConfigurableApplicationContext context = SpringApplication.run(ZooApplication.class, args);
		Animal chosenAnimal = context.getBean(Animal.class);

		printWelcome();
		readUserInput(chosenAnimal);

		while (true) {
			System.out.println("Do you want to get information of another animal? (y/n)");
			Scanner input = new Scanner(System.in);
			String ans = input.next();
			if (ans.equals("y")) {
				System.out.println("Please select what species of animal you want to see more information on:");
				readUserInput(chosenAnimal);
			}
			else {
				System.out.println("Exiting...");
				break;
			}
		}
		context.close();
	}

	public static void printWelcome(){
		System.out.println("Hello and welcome to our zoo!");
		System.out.println("Please select what species of animal you want to see more information on:");
	}

	public static void readUserInput(Animal chosenAnimal) {
		printSpecies("Zoo");

		Scanner input = new Scanner(System.in);

		String animalType = input.next();

		System.out.println("Please select an Animal to display information:");
		printSpecies(animalType);

		String name = input.next();

		chosenAnimal.setAll(name, animalType);

		System.out.println("Daily cost to feed animal: " + round(chosenAnimal.getFood().getFoodPrice().getCost()));

	}
	public static void printSpecies(String xmlTarget) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("src\\main\\resources\\zoo.xml"));
			document.getDocumentElement().normalize();

			NodeList speciesList = document.getElementsByTagName(xmlTarget);

			NodeList speciesNodeList = speciesList.item(0).getChildNodes();

			for (int i = 0 ; i < speciesNodeList.getLength() ; i++) {
				Node detail = speciesNodeList.item(i);
				if (detail.getNodeType() == Node.ELEMENT_NODE) {
					Element detailElement = (Element) detail;
					if(xmlTarget.equals("Zoo")) {
						System.out.println(detailElement.getTagName());
					}
					else {
						System.out.println(detailElement.getAttribute("name"));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error reading xml ZooApplication: " + e);
		}
	}
}
