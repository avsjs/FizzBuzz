package com.fizzbuzz.game.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

import static com.fizzbuzz.game.util.FizzBuzzUtil.ONE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class FizzBuzzServiceImplTest {

    private FizzBuzzService fizzBuzzService;

    @Before
    public void setUp() {
        this.fizzBuzzService = new FizzBuzzServiceImpl();
    }

    @Test
    public void getFizzBuzzNumbersForSingleNumberTest() {
        assertThat(fizzBuzzService.getFizzBuzzNumbersForSingleNumber(1)).isEqualTo(Collections.singletonList("1"));
        assertThat(fizzBuzzService.getFizzBuzzNumbersForSingleNumber(5)).isEqualTo(Arrays.asList("1", "2", "Fizz", "4", "Buzz"));
        assertThat(fizzBuzzService.getFizzBuzzNumbersForSingleNumber(-5)).isEqualTo(Arrays.asList("Buzz", "-4", "Fizz", "-2", "-1", "FizzBuzz"));
        assertThat(fizzBuzzService.getFizzBuzzNumbersForSingleNumber(10)).hasSize(10);
        assertThat(fizzBuzzService.getFizzBuzzNumbersForSingleNumber(1)).isNotEqualTo(Collections.singletonList("Fizz"));
        assertThat(fizzBuzzService.getFizzBuzzNumbersForSingleNumber(5)).isNotEqualTo(Arrays.asList("1", "2", "Fizz", "FizzBuzz", "Buzz"));
    }

    @Test
    public void getFizzBuzzNumbersInRangeTest() {
        assertThat(fizzBuzzService.getFizzBuzzNumbersInRange(10, 15)).isEqualTo(Arrays.asList("Buzz", "11", "Fizz", "13", "14", "FizzBuzz"));
        assertThat(fizzBuzzService.getFizzBuzzNumbersInRange(30, 25)).isEqualTo(Arrays.asList("Buzz", "26", "Fizz", "28", "29", "FizzBuzz"));
        assertThat(fizzBuzzService.getFizzBuzzNumbersInRange(-30, -25)).isEqualTo(Arrays.asList("FizzBuzz", "-29", "-28", "Fizz", "-26", "Buzz"));
        assertThat(fizzBuzzService.getFizzBuzzNumbersInRange(11, 20)).hasSize(10);
        assertThat(fizzBuzzService.getFizzBuzzNumbersInRange(10, 15)).isNotEqualTo(Arrays.asList("Buzz", "11", "Fizz", "13", "14", "15"));
        assertThat(fizzBuzzService.getFizzBuzzNumbersInRange(-30, -25)).isNotEqualTo(Arrays.asList("-30", "-29", "-28", "Fizz", "-26", "Buzz"));
    }

    @Test
    public void getFizzBuzzForMultipleNumbersTest() {
        assertThat(fizzBuzzService.getFizzBuzzForMultipleNumbers(Arrays.asList(12, 15, 100000000))).isEqualTo(Arrays.asList("Fizz", "FizzBuzz", "Buzz"));
        assertThat(fizzBuzzService.getFizzBuzzForMultipleNumbers(Arrays.asList(-10, 3, -15, -131))).isEqualTo(Arrays.asList("Buzz", "Fizz", "FizzBuzz", "-131"));
        assertThat(fizzBuzzService.getFizzBuzzForMultipleNumbers(Arrays.asList(12, 1, 100000000))).isNotEqualTo(Arrays.asList("Fizz", "FizzBuzz", "Buzz"));
    }

    @Ignore("For manual performance testing")
    @Test
    public void manualPerformanceTest() throws Exception {
        final long start = System.currentTimeMillis();
        final int number = 10000;
        IntStream.range(ONE, number).forEachOrdered(fizzBuzzService::getFizzBuzzNumbersForSingleNumber);
        final long finish = System.currentTimeMillis();
        System.out.println(String.format("Tested: %d in %d milliseconds", number, (finish - start)));
    }
}