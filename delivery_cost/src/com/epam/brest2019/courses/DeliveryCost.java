package com.epam.brest2019.courses;

import com.epam.brest2019.courses.calculator.Calculator;
import com.epam.brest2019.courses.calculator.impl.CalculatorImpl;
import com.epam.brest2019.courses.reader.ConsoleReader;
import com.epam.brest2019.courses.reader.FileReader;
import com.epam.brest2019.courses.reader.impl.BigDecemalConsoleReader;
import com.epam.brest2019.courses.reader.impl.PropertiesFileReader;
import com.epam.brest2019.courses.scale.ValueScale;
import com.epam.brest2019.courses.scale.impl.PropertiesDistanceScale;
import com.epam.brest2019.courses.scale.impl.PropertiesWeightScale;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class DeliveryCost {

    private final static String PRICE_PER_KG = "price_per_kg.properties";
    private final static String PRICE_PER_KM = "price_per_km.properties";

    public static void main(String[] args) {

        BigDecimal weight;
        BigDecimal distance;
        BigDecimal pricePerKg;
        BigDecimal pricePerKm;
        Map<String, BigDecimal> weightMap = null;
        Map<String, BigDecimal> distanceMap = null;
        ConsoleReader<BigDecimal> consoleReader = new BigDecemalConsoleReader();
        FileReader fileReader = new PropertiesFileReader();
        ValueScale weightScale = new PropertiesWeightScale();
        ValueScale distanceScale = new PropertiesDistanceScale();
        Calculator calculator = new CalculatorImpl();

        distance = consoleReader.readData("Enter the distance in kilometers or 'q' for quit");
        weight = consoleReader.readData("Enter the weight in kilograms or 'q' for quit");
        try {
            weightMap = fileReader.readData(PRICE_PER_KG);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            distanceMap = fileReader.readData(PRICE_PER_KM);
        } catch (IOException e) {
            e.printStackTrace();
        }

        pricePerKg = weightScale.getValue(weightMap, weight);

        pricePerKm = distanceScale.getValue(distanceMap, distance);

        System.out.println("distance = " + distance);
        System.out.println("weight = " + weight);
        System.out.println("pricePerKm = " + pricePerKm);
        System.out.println("pricePerKg = " + pricePerKg);


        BigDecimal price = calculator.countDeliveryCost(weight, distance, pricePerKg, pricePerKm);
        System.out.println("Price = " + price);

    }

}
