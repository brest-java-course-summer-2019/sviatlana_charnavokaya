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

        BigDecimal pricePerKg = new BigDecimal("30");
        BigDecimal pricePerKm = new BigDecimal("50");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the weigth in kilograms or 'q' for quit");
        String inputString = scanner.nextLine();

        if (!inputString.toLowerCase().equals("q")) {
            try {
                weight = new BigDecimal(inputString);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong value BigDecimal");
            }
        } else {
            System.out.println("Bye!");
            return;
        }

        System.out.println("Enter the distance in kilometers or 'q' for quit");
        inputString = scanner.nextLine();
        if (!inputString.toLowerCase().equals("q")) {
            try {
                distance = new BigDecimal(inputString);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong value BigDecimal");
            }

        } else {
            System.out.println("Bye!");
            return;
        }

        FileInputStream fis;
        Properties properties = new Properties();

        try {
            fis = new FileInputStream("delivery_cost/resources/price_per_km.properties");
            properties.load(fis);

            String dist1 = properties.getProperty("less10");
            String dist2 = properties.getProperty("less100");
            String dist3 = properties.getProperty("less500");
            String dist4 = properties.getProperty("less1000");

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }



        System.out.println("weight = " + weight);
        System.out.println("distance = " + distance);

        BigDecimal price = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
        System.out.println("Price = " + price);

    }
}
