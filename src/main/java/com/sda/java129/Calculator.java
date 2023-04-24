package com.sda.java129;

public class Calculator {
    public double add(double a, double b) {
        return a + b;
    }

    public double root(double x) {
        if(x < 0)
            throw new RootException("Number smaller than 0");
        return Math.sqrt(x);
    }

    public double sum(double... numbers) {
        double sum = 0d;
        for (double number : numbers)
            sum += number;
        return sum;
    }
}
