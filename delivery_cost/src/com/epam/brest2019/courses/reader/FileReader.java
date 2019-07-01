package com.epam.brest2019.courses.reader;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public interface FileReader <K, V>{

    Map<K, V> readData(final String filePath) throws IOException;

    BigDecimal readPricePerKmFromProperties(BigDecimal distance);
    BigDecimal readPricePerKgFromProperties(BigDecimal weight);
}
