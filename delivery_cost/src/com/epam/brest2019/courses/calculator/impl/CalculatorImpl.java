package com.epam.brest2019.courses.calculator.impl;

import com.epam.brest2019.courses.calculator.Calculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculatorImpl implements Calculator {
    @Override
    public BigDecimal countDeliveryCost(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm) {
        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
    }
}
