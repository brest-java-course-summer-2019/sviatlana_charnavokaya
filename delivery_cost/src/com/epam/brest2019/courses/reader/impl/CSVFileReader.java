package com.epam.brest2019.courses.reader.impl;

import com.epam.brest2019.courses.reader.FileReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CSVFileReader implements FileReader <Integer, BigDecimal>{

    @Override
    public Map<Integer, BigDecimal> readData(String filePath) throws IOException {
        String paths = Objects.requireNonNull(CSVFileReader.class.getClassLoader().getResource(filePath).getPath());

        Map<Integer, BigDecimal> resultMap;
        try (Stream<String> lines = Files.lines(Paths.get(paths))) {
            resultMap =
                    lines.map(line -> line.split(","))
                            .collect(Collectors.toMap(line -> Integer.parseInt(line[0]), line -> new BigDecimal(line[1])));
        }
        return resultMap;
    }

}
