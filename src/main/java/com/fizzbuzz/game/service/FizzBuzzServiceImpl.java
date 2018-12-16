package com.fizzbuzz.game.service;

import com.fizzbuzz.game.util.FizzBuzzUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.fizzbuzz.game.util.FizzBuzzUtil.ZERO;
import static com.fizzbuzz.game.util.FizzBuzzUtil.getNumbers;

@Service("fizzBuzzService")
public class FizzBuzzServiceImpl implements FizzBuzzService {
    final static Logger logger = Logger.getLogger(FizzBuzzUtil.class);

    @Override
    public List<String> getFizzBuzzNumbersForSingleNumber(int number) {
        logger.info("Starting method getFizzBuzzNumbersForSingleNumber... ");
        return number <= ZERO ? getNumbers(number, ZERO) : getNumbers(FizzBuzzUtil.ONE, number);
    }

    @Override
    public List<String> getFizzBuzzNumbersInRange(int firstNumber, int lastNumber) {
        logger.info("Starting method getFizzBuzzNumbersInRange... ");
        return firstNumber <= lastNumber ? getNumbers(firstNumber, lastNumber) : getNumbers(lastNumber, firstNumber);
    }

    @Override
    public List<String> getFizzBuzzForMultipleNumbers(List<Integer> numbers) {
        logger.info("Starting method getFizzBuzzForMultipleNumbers... ");
        return numbers.stream().map(FizzBuzzUtil::fizzBuzz).collect(Collectors.toList());
    }
}
