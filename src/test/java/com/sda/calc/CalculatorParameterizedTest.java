package com.sda.calc;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorParameterizedTest {

    private Calculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @MethodSource("provideNumbersToAdd")
    public void testAdd(double a, double b){
        Double result = calculator.add(a, b);

        assertThat(result).isEqualTo(a+b);
    }

    private static Stream<Arguments> provideNumbersToAdd(){
        return Stream.of(
                Arguments.of(1.25d, 3.5d),
                Arguments.of(5.5d, 5d)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {5.5, 10d, 4d})
    public void testRoot(double number){
        Double result = calculator.root(number);

        assertThat(result).isEqualTo(Math.sqrt(number));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-5d, -10d})
    public void testRootExceptions(double number){
        RootException e = assertThrows(RootException.class, () -> calculator.root(number));

        assertThat(e.getMessage()).isEqualTo("Can't root a negative number.");
    }
}
