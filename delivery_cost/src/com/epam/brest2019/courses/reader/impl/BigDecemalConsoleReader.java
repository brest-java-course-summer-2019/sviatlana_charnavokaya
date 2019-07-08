package com.epam.brest2019.courses.reader.impl;

import com.epam.brest2019.courses.reader.ConsoleReader;

import java.math.BigDecimal;
import java.util.Scanner;

public class BigDecemalConsoleReader implements ConsoleReader<BigDecimal> {

    public static final String QUIT_SYMBOL = "q";

    @Override
    public BigDecimal readData(String massage) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(massage);
        String inputString = null;
        boolean flag = true;

        do {
            while (!scanner.hasNextBigDecimal()){
                inputString = scanner.nextLine();
                if (inputString.toLowerCase().equals(QUIT_SYMBOL)) {
                    System.out.println("Bye!");
                    System.exit(0);
                }
                System.out.println("Value should be number. Try again: " + massage);
            }
            //inputString = scanner.nextLine();

            if (scanner.hasNextBigDecimal()){
                inputString = scanner.nextLine();
                if (new BigDecimal(inputString).compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Value should positive. Try again: " + massage);
                } else {
                    flag = false;}
            }

        } while (flag);

        //inputString = scanner.nextLine();
        //scanner.close();
        return new BigDecimal(inputString);
    }
}
