package com.epam.brest2019.courses.reader.impl;

import com.epam.brest2019.courses.reader.FileReader;
import com.epam.brest2019.courses.scale.ValueScale;
import com.epam.brest2019.courses.scale.impl.PropertiesWeightScale;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesFileReader implements FileReader <String, BigDecimal> {

    private ValueScale<String, BigDecimal> valueScale = new PropertiesWeightScale();

      @Override
    public Map<String, BigDecimal> readData(String path) throws IOException {
        InputStream inputStream = PropertiesFileReader.class.getClassLoader().getResourceAsStream(path);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties.entrySet().stream().collect(
                Collectors.toMap(e -> String.valueOf(e.getKey()),
                        e -> new BigDecimal(e.getValue().toString())));
    }




}
