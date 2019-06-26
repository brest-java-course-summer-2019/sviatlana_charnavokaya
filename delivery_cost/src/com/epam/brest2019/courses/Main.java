package com.epam.brest2019.courses;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BigDecimal weight;
        BigDecimal distance;
        BigDecimal pricePerKg;
        BigDecimal pricePerKm;

        distance = readDataFromConsole("Enter the distance in kilometers or 'q' for quit");
        weight = readDataFromConsole("Enter the weigth in kilograms or 'q' for quit");
        pricePerKg = readPricePerKmFromProperties(distance);
        pricePerKm = readPricePerKgFromProperties(weight);

        System.out.println("weight = " + weight);
        System.out.println("distance = " + distance);
        System.out.println("pricePerKg = " + pricePerKg);
        System.out.println("pricePerKm = " + pricePerKm);

        BigDecimal price = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
        System.out.println("Price = " + price);


    }

    public static BigDecimal readDataFromConsole(String massage) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(massage);
        String inputString;

        while (!scanner.hasNextBigDecimal()){
            inputString = scanner.nextLine();
            if (inputString.toLowerCase().equals("q")) {
                System.out.println("Bye!");
                System.exit(0);
            }
            System.out.println("Incorrect data. " + massage);

        }
        inputString = scanner.nextLine();
        return new BigDecimal(inputString);
    }

    public static BigDecimal readPricePerKmFromProperties(BigDecimal distance){

        FileInputStream fis;
        Properties properties = new Properties();

        try {
            fis = new FileInputStream("delivery_cost/resources/price_per_km.properties");
            properties.load(fis);

        } catch (IOException e) {
            System.err.println("Error: The file does not exist!");
        }

        if (distance.doubleValue() <  10){
            return new BigDecimal(properties.getProperty("less10"));
        }else if (distance.doubleValue() >=  10 && distance.doubleValue() < 100) {
                return new BigDecimal(properties.getProperty("less100"));
        }else if (distance.doubleValue() >=  100 && distance.doubleValue() < 500) {
                return new BigDecimal(properties.getProperty("less500"));
        }else if (distance.doubleValue() >=  500 && distance.doubleValue() < 1000) {
                return new BigDecimal(properties.getProperty("less1000"));
        }else return new BigDecimal(properties.getProperty("less1000"));

    }


    public static BigDecimal readPricePerKgFromProperties(BigDecimal weight){

        FileInputStream fis;
        Properties properties = new Properties();

        try {
            fis = new FileInputStream("delivery_cost/resources/price_per_kg.properties");
            properties.load(fis);

        } catch (IOException e) {
            System.err.println("Error: The file does not exist!");
        }

        if (weight.doubleValue() <  100){
            return new BigDecimal(properties.getProperty("less100"));
        }else if (weight.doubleValue() >=  100 && weight.doubleValue() < 500) {
            return new BigDecimal(properties.getProperty("less500"));
        }else if (weight.doubleValue() >=  500 && weight.doubleValue() < 1500) {
            return new BigDecimal(properties.getProperty("less1500"));
        }else if (weight.doubleValue() >=  1500 && weight.doubleValue() < 2000) {
            return new BigDecimal(properties.getProperty("less2000"));
        }else return new BigDecimal(properties.getProperty("more2000"));

    }


}
