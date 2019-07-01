package com.epam.brest2019.courses.calculator;

import java.math.BigDecimal;

public interface Calculator {

    BigDecimal countDeliveryCost(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm);
}
