package com.epam.brest2019.courses.scale.impl;

import com.epam.brest2019.courses.scale.ValueScale;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

@Component
public class CSVValueScale implements ValueScale <Integer, BigDecimal> {
    @Override
    public BigDecimal getValue(Map<Integer, BigDecimal> map, BigDecimal targetKey) {

        SortedSet<Integer> sortedKeys = new TreeSet<>(map.keySet());
        Integer foundedKey = sortedKeys.first();

        for(Integer key : sortedKeys) {
            if (foundedKey >= targetKey.doubleValue()) {
                break;
            }
            foundedKey = key;
        }

        BigDecimal foundedValue = map.get(foundedKey);
        System.out.format("Selected interval for value %.2f is %d -> %.2f$%n", targetKey, foundedKey, foundedValue);
        return foundedValue;
    }
}
