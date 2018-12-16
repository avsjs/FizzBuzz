package com.fizzbuzz.game.service;

import java.util.List;

/**
 * FizzBuzz service provides the list of FizzBuzz values for a single number, between two numbers (numbers in range) or for multiple numbers.
 * Input numbers should be in the range -1000000 to 1000000.
 */
public interface FizzBuzzService {

    /**
     * Returns FizzBuzz values for the given number.
     *
     * @param number
     * @return List of FizzBuzz values
     */
    List<String> getFizzBuzzNumbersForSingleNumber(int number);

    /**
     * Returns FizzBuzz values between first and last number inclusive.
     * If given first number is greater or less than last number, then it returns FizzBuzz values in ascending order.
     *
     * @param firstNumber
     * @param lastNumber
     * @return List of FizzBuzz values
     */
    List<String> getFizzBuzzNumbersInRange(int firstNumber, int lastNumber);

    /**
     * Returns FizzBuzz numbers for the given multiple numbers.
     * Input numbers separated by comma(,).
     *
     * @param numbers
     * @return List of FizzBuzz values
     */
    List<String> getFizzBuzzForMultipleNumbers(List<Integer> numbers);
}
