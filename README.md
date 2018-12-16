# FizzBuzz Api
Rest Api Based Fizz Buzz Game

An API to retrieve [FizzBuzz](https://en.wikipedia.org/wiki/Fizz_buzz) numbers.

> Fizz buzz is a group word game for children to teach them about division.[1] Players take turns to count incrementally, replacing any number divisible by three with the word "fizz", and any number divisible by five with the word "buzz".

# Prerequisites
Java 8, Spring boot and maven

# How to build and run in local

1. to build : > mvn clean install
2. to run 	: > mvn spring-boot:run 

### Web Url : [FizzBuzz API Web Url](https://fizzbuzz-avs.herokuapp.com/swagger-ui.html#!/fizz-buzz-controller)

### Examples

#### Successful requests
Below are the multiple ways for playing Fizz Buzz Game
- https://fizzbuzz-avs.herokuapp.com/fizzbuzz/api/10
    
    [
      "1",
      "2",
      "Fizz",
      "4",
      "Buzz",
      "Fizz",
      "7",
      "8",
      "Fizz",
      "Buzz"
    ]
- https://fizzbuzz-avs.herokuapp.com/fizzbuzz/api/numbersInRange?firstNumber=10&lastNumber=20
  
    [
      "Buzz",
      "11",
      "Fizz",
      "13",
      "14",
      "FizzBuzz",
      "16",
      "17",
      "Fizz",
      "19",
      "Buzz"
    ]
    
- https://fizzbuzz-avs.herokuapp.com/fizzbuzz/api/multipleNumbers?numbers=12,123,1577,1000005,50
    
    [
      "Fizz",
      "Fizz",
      "1577",
      "FizzBuzz",
      "Buzz"
    ]
        
 #### Erroneous requests    
    
 - https://fizzbuzz-avs.herokuapp.com/fizzbuzz/api/numbersInRange?firstNumber=werwr&lastNumber=1232
    
    {
      "timestamp": "2018-10-15T20:00:11.971+0000",
     "status": 400,
     "error": "Bad Request",
     "message": "Please provide a valid number or valid request",
     "path": "/fizzbuzz/api/numbersInRange"
    }
    
  - https://fizzbuzz-avs.herokuapp.com/fizzbuzz/api/-10000000
    
    "Number must be greater than or equal to -1000000"
    
  - https://fizzbuzz-avs.herokuapp.com/fizzbuzz/api/multipleNumbers?numbers=12,123,wfew
  
     {
       "timestamp": "2018-10-15T20:02:05.010+0000",
       "status": 400,
       "error": "Bad Request",
       "message": "Please provide a valid number or valid request",
       "path": "/fizzbuzz/api/multipleNumbers"
     }
