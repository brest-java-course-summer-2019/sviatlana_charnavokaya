package com.epam.brest2019.courses;

import jdk.swing.interop.SwingInterOpUtils;

public class Main1111 {

   public static void main(String[] args) {
       Main1111 main = new Main1111();
       System.out.println(main.getString());
       System.out.println(main.calc(12f, 13d));



       System.out.println("Hi");
        Integer newInteger = 12;

        // TODO: next

        System.out.println(newInteger);

       double f = 0.0;
       for (int i = 1; i <= 10; i++){
           f += 0.1;
       }
       System.out.println("result" + f);
    }

    public String getString(){
        return "TEST";
    }

    public Double calc(Float f, Double d){
        return d + f;
    }



}