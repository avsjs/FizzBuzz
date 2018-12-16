package com.fizzbuzz.game.controller;


import com.fizzbuzz.game.service.FizzBuzzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("/fizzbuzz/api")
@Api(value = "/fizzbuzz/api", description = "Multiple ways for playing Fizz Buzz Game")
public class FizzBuzzController {

    private static final String SINGLE_NUMBER_SUMMARY = "Returns a list of FizzBuzz values for single number as input";
    private static final String SINGLE_NUMBER_NOTES = "<p>Input parameter 'number' can be used to narrow the result set. Returns a list of FizzBuzz values upto the given number starting from 1.</p><p>Minimum and Maximum number is allowed is from -1000000 to 1000000.</p>";
    private static final String BETWEEN_INRANGE_SUMMARY = "Returns a list of FizzBuzz values for the numbers between first number and last number";
    private static final String BETWEEN_INRANGE_NOTES = "<p>Request parameters 'firstNumber' and 'lastNumber' can be used to narrow the result set. Returns a list of FizzBuzz values for the numbers between first number and last number.<p>First number can be greater than or less than last number, result will be in ascending order.</p>";
    private static final String MULTIPLE_NUMBERS_SUMMARY = "Returns a list of FizzBuzz values for the provided multiple numbers";
    private static final String MULTIPLE_NUMBERS_NOTES = "<p>Request parameter 'numbers' can be used to narrow the result set. Add multiple numbers separated by comma(',') or new line in the 'Numbers' field. Returns a list of FizzBuzz values.</p>";
    @Autowired
    FizzBuzzService fizzBuzzService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "Bad request")
    }
    )

    @RequestMapping(method = RequestMethod.GET, value = "/{number}", produces = "application/json")
    @ApiOperation(value = SINGLE_NUMBER_SUMMARY, notes = SINGLE_NUMBER_NOTES, response = Iterable.class, responseContainer = "List" )
    public Iterable<String> getFizzBuzzNumbers(@PathVariable("number") @Valid @Max(1000000) @Min(-1000000) int number) {
        return fizzBuzzService.getFizzBuzzNumbersForSingleNumber(number);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/numbersInRange", produces = {"application/JSON"})
    @ApiOperation(value = BETWEEN_INRANGE_SUMMARY, notes = BETWEEN_INRANGE_NOTES, response = Iterable.class, responseContainer = "List" )
    public Iterable<String> getFizzBuzzNumbersInRange(@RequestParam("firstNumber") @Valid int firstNumber, @RequestParam("lastNumber") @Valid int lastNumber) {
        return fizzBuzzService.getFizzBuzzNumbersInRange(firstNumber, lastNumber);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/multipleNumbers", produces = {"application/JSON"})
    @ApiOperation(value = MULTIPLE_NUMBERS_SUMMARY, notes = MULTIPLE_NUMBERS_NOTES, response = Iterable.class, responseContainer = "List")
    public Iterable<String> getMultipleFizzBuzzNumbers(@RequestParam("numbers") @Valid List<@NotNull(message = "{multiple.numbers.notNull}") Integer> numbers) {
        return fizzBuzzService.getFizzBuzzForMultipleNumbers(numbers);
    }

    @ExceptionHandler
    private String constraintViolationHandler(ConstraintViolationException exception) {
        return exception.getConstraintViolations().iterator().next()
                .getMessage();
    }
}
