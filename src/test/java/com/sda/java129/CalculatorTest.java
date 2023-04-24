package com.sda.java129;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    void add() {
        // When
        double result = calculator.add(5, 10);

        // Then
        //assertEquals(15, result);
        assertThat(result)
                .isEqualTo(15);
    }

    @ParameterizedTest
    @MethodSource("paramsForTestRootSuccess")
    void testRootSuccess(double x, double root){
        assertThat(calculator.root(x)).isEqualTo(root);
    }

    private static Stream<Arguments> paramsForTestRootSuccess(){
        return Stream.of(
            Arguments.of(4d, 2d),
            Arguments.of(9d, 3d),
            Arguments.of(16d, 4d),
            Arguments.of(2.25, 1.5)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {-2.5, -10.5, -0.6666})
    void testRootFail(double x){
        RootException exception = assertThrows(RootException.class, () -> calculator.root(x));

        assertThat(exception).hasMessage("Number smaller than 0");
    }

    @ParameterizedTest
    @MethodSource("paramsForTestSumSuccess")
    void testSumSuccess(double[] toSum, double sum){
        assertThat(calculator.sum(toSum)).isEqualTo(sum);
    }

    private static Stream<Arguments> paramsForTestSumSuccess(){
        return Stream.of(
                Arguments.of(new double[]{1d, 2d, 3d}, 6d),
                Arguments.of(new double[]{1.5, 2.5, 3.5}, 7.5),
                Arguments.of(new double[]{0.75, 2.25}, 3d)
        );
    }
}