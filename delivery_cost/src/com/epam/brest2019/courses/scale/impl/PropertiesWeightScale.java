package com.epam.brest2019.courses.scale.impl;

import com.epam.brest2019.courses.scale.ValueScale;

import java.math.BigDecimal;
import java.util.Map;

public class PropertiesWeightScale implements ValueScale<String, BigDecimal> {
    @Override
    public BigDecimal getValue(Map<String, BigDecimal> map, BigDecimal weight) {
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


