package com.fizzbuzz.game.util;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzUtilTest {

    @Test
    public void getNumbersTest() {
        assertThat(FizzBuzzUtil.getNumbers(10, 15)).isEqualTo(Arrays.asList("Buzz", "11", "Fizz", "13", "14", "FizzBuzz"));
        assertThat(FizzBuzzUtil.getNumbers(-30, -25)).isEqualTo(Arrays.asList("FizzBuzz", "-29", "-28", "Fizz", "-26", "Buzz"));
        assertThat(FizzBuzzUtil.getNumbers(11, 20)).hasSize(10);
        assertThat(FizzBuzzUtil.getNumbers(30, 25)).isNotEqualTo(Arrays.asList("Buzz", "26", "Fizz", "28", "29", "FizzBuzz"));
        assertThat(FizzBuzzUtil.getNumbers(10, 15)).isNotEqualTo(Arrays.asList("Buzz", "11", "Fizz", "13", "14", "15"));
        assertThat(FizzBuzzUtil.getNumbers(-30, -25)).isNotEqualTo(Arrays.asList("-30", "-29", "-28", "Fizz", "-26", "Buzz"));
    }

    @Test
    public void fizzBuzzTest() {
        assertThat(FizzBuzzUtil.fizzBuzz(10)).isEqualTo("Buzz");
        assertThat(FizzBuzzUtil.fizzBuzz(15)).isEqualTo("FizzBuzz");
        assertThat(FizzBuzzUtil.fizzBuzz(3)).isEqualTo("Fizz");
        assertThat(FizzBuzzUtil.fizzBuzz(13)).isEqualTo("13");
        assertThat(FizzBuzzUtil.fizzBuzz(10)).isNotEqualTo("Fizz");
        assertThat(FizzBuzzUtil.fizzBuzz(3)).isNotEqualTo("Buzz");
        assertThat(FizzBuzzUtil.fizzBuzz(5)).isNotEqualTo("Fizz");
        assertThat(FizzBuzzUtil.fizzBuzz(15)).isNotEqualTo("15");
    }
}