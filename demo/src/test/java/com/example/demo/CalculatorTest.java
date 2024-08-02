package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    void testSum(){
        Calculator calculator = new Calculator();
        int actual = calculator.sum(2, 3);
        int expected = 5;
        Assertions.assertEquals(expected, actual);


    }
}
