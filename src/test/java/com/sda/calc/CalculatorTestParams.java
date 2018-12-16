package com.sda.calc;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class CalculatorTestParams {

    private Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    @Parameters({"1d,2d", "8.5,3.5"})
    public void testAdd(double valueA, double valueB) {
        assertThat(calculator.add(valueA, valueB)).isEqualTo(valueA + valueB);
    }

    @Test
    @Parameters({"1d,2d", "8.5,3.5"})
    public void testSubstract(double valueA, double valueB) {
        assertThat(calculator.substract(valueA, valueB)).isEqualTo(valueA - valueB);
    }

    //Params from provider class
    @Test
    @Parameters(source = DoubleProvider.class)
    public void testMultiply(double valueA, double valueB) {
        assertThat(calculator.multiply(valueA, valueB)).isEqualTo(valueA * valueB);
    }

    //Params from method below, identified by name.
    @Test
    @Parameters //(method = "methodName")
    public void testDivide(double valueA, double valueB) {
        assertThat(calculator.divide(valueA, valueB)).isEqualTo(valueA / valueB);
    }

    /* when method name is not specified i @Parameters annotaion,
    method must me named -> "parametersFor" + testMethodName */
    private Double[][] parametersForTestDivide() {
        return new Double[][]{
                new Double[]{1d, 2d},
                new Double[]{8.5, 3.5}
        };
    }

    @Test
    @Parameters({"1d", "8.5"})
    public void testRoot(double valueA) {
        assertThat(calculator.root(valueA)).isNotNull().isEqualTo(Math.sqrt(valueA));
    }

}
