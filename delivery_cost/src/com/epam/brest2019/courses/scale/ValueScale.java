package com.epam.brest2019.courses.scale;

import java.math.BigDecimal;
import java.util.Map;

public interface ValueScale <K, V>{
    BigDecimal getValue(Map<K, V> map, BigDecimal key);
}
