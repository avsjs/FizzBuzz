package com.fizzbuzz.game.util;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.rangeClosed;

/**
 * FizzBuzz utility has following methods.
 * 1. Check FizzBuzz for single value.
 * 2. Get list of FizzBuzz values between first number and last number.
 * 3. Predicate to check input value divisible by n.
 * <p>
 * Static variables like ONE and ZERO.
 * </p>
 */

public class FizzBuzzUtil {
    /* Default comment */
    public final static int ONE = 1;
    /* Default comment */
    public final static int ZERO = 0;
    final static Logger logger = Logger.getLogger(FizzBuzzUtil.class);

    /**
     * Returns predicate Integer value for input value divisible by 3 or 5
     *
     * @param number
     * @return Predicate Integer value
     */
    public static Predicate<Integer> divBy(int number) {
        try {
            return i -> (i % number) == 0;
        } catch (ArithmeticException ex) {
            logger.error("Number cannot divisible by 0", ex);
        }
        return null;
    }

    /**
     * Returns FizzBuzz values between first and last number inclusive.
     *
     * @param firstNumber
     * @param lastNumber
     * @return list of FizzBuzz values.
     */
    public static List<String> getNumbers(int firstNumber, int lastNumber) {
        return rangeClosed(firstNumber, lastNumber).mapToObj(FizzBuzzUtil::fizzBuzz).collect(Collectors.toList());
    }

    /**
     * Returns FizzBuzz value for the given number.
     *
     * @param value
     * @return FizzBuzz value.
     */
    public static String fizzBuzz(Integer value) {
        String fizz = divBy(3).test(value) ? "Fizz" : "";
        String buzz = divBy(5).test(value) ? "Buzz" : "";
        return (fizz.isEmpty() && buzz.isEmpty()) ? value.toString() : fizz + buzz;
    }
}
