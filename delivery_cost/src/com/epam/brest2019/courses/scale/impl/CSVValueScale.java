package com.epam.brest2019.courses.scale.impl;

import com.epam.brest2019.courses.scale.ValueScale;

import java.math.BigDecimal;
import java.util.Map;

public class CSVValueScale implements ValueScale <Integer, BigDecimal> {
    @Override
    public BigDecimal getValue(Map<Integer, BigDecimal> map, BigDecimal key) {
        return null;
    }
}
