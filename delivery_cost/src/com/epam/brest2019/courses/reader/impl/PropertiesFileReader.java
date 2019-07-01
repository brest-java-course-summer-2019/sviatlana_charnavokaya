package com.epam.brest2019.courses.reader.impl;

import com.epam.brest2019.courses.reader.FileReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesFileReader implements FileReader <String, BigDecimal> {


       /* Properties properties = new Properties();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        properties.load(classloader.getResourceAsStream("price.properties"));*/

      @Override
    public Map<String, BigDecimal> readData(String path) throws IOException {
        InputStream inputStream = PropertiesFileReader.class.getClassLoader().getResourceAsStream(path);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties.entrySet().stream().collect(
                Collectors.toMap(e -> String.valueOf(e.getKey()),
                        e -> new BigDecimal(e.getValue().toString())));
    }




    @Override
    public BigDecimal readPricePerKmFromProperties(BigDecimal distance) {
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

    @Override
    public BigDecimal readPricePerKgFromProperties(BigDecimal weight) {
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

    public BigDecimal readPricePerKgFromProperties(Map<String, BigDecimal> map, BigDecimal weight) {

        if (weight.doubleValue() <  100){
            return map.get("less100");
        }else if (weight.doubleValue() >=  100 && weight.doubleValue() < 500) {
            return map.get("less500");
        }else if (weight.doubleValue() >=  500 && weight.doubleValue() < 1500) {
            return map.get("less1500");
        }else if (weight.doubleValue() >=  1500 && weight.doubleValue() < 2000) {
            return map.get("less2000");
        }else return map.get("more2000");
    }


}
