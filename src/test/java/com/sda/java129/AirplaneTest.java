package com.sda.java129;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.sda.java129.Airplane.MAX_POSITION;
import static com.sda.java129.Airplane.MIN_POSITION;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AirplaneTest {
    private Airplane airplane;

    /*
    Test cases:
    - ascent above 1 000 000
    - ascent with negative value
    - ascent with correct parameters
    - descent below 0
    - descent with negative value
    - descent witch correct parameters
     */

    @BeforeEach
    public void setup(){
        airplane = new Airplane(1000);
    }

    @Test
    void ascendAbove1m() {
        airplane.ascend(MAX_POSITION + 1);

        // result = 1 000 000
        assertEquals(MAX_POSITION, airplane.getPosition());
    }

    @Test
    void ascendWithNegativeValue() {
        airplane.ascend(-100);

        // result = old value
        assertEquals(1000, airplane.getPosition());
    }

    @Test
    void ascendCorrect() {
        airplane.ascend(100);

        // result = old value + 100
        assertEquals(1100, airplane.getPosition());
    }

    @Test
    void descendBelow0() {
        airplane.descend(1_000_001);

        assertEquals(MIN_POSITION, airplane.getPosition());
    }

    @Test
    void descendWithNegativeValue() {
        airplane.descend(-100);

        assertEquals(1000, airplane.getPosition());
    }

    @Test
    void descendCorrect() {
        airplane.descend(100);

        assertEquals(900, airplane.getPosition());
    }
}