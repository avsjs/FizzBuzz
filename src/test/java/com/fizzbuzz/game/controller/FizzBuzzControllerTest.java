package com.fizzbuzz.game.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fizzbuzz.game.FizzbuzzApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FizzbuzzApplication.class)
@WebAppConfiguration
public class FizzBuzzControllerTest {

    private static final String URL_PREFIX = "/fizzbuzz/api/";
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void getFizzBuzzNumbersWithValidInput() throws Exception {
        String uri = URL_PREFIX + "1";
        MvcResult mvcResult = getMvcResult(uri);
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);
        List content = mapFromJson(mvcResult.getResponse().getContentAsString(), List.class);
        assertThat(content).isEqualTo(Collections.singletonList("1"));
    }

    @Test
    public void testArithimaticException() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(URL_PREFIX + "/api/api"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getFizzBuzzNumbersInRangeWithValidInput() throws Exception {
        String uri = URL_PREFIX + "numbersInRange?firstNumber=11&lastNumber=15";
        MvcResult mvcResult = getMvcResult(uri);
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);
        List content = mapFromJson(mvcResult.getResponse().getContentAsString(), List.class);
        assertThat(content).isEqualTo(Arrays.asList("11", "Fizz", "13", "14", "FizzBuzz"));
    }

    @Test
    public void getFizzBuzzNumbersInRangeWithDescendingOrder() throws Exception {
        String uri = URL_PREFIX + "numbersInRange?firstNumber=-21&lastNumber=-10";
        MvcResult mvcResult = getMvcResult(uri);
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);
        List content = mapFromJson(mvcResult.getResponse().getContentAsString(), List.class);
        assertThat(content).isEqualTo(Arrays.asList("Fizz", "Buzz", "-19", "Fizz", "-17", "-16", "FizzBuzz", "-14", "-13", "Fizz", "-11", "Buzz"));
    }

    @Test
    public void getMultipleFizzBuzzNumbers() throws Exception {
        String uri = URL_PREFIX + "multipleNumbers?numbers=12,15,100,133,-155,-110002";
        MvcResult mvcResult = getMvcResult(uri);
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);
        List content = mapFromJson(mvcResult.getResponse().getContentAsString(), List.class);
        assertThat(content).isEqualTo(Arrays.asList("Fizz", "FizzBuzz", "Buzz", "133", "Buzz", "-110002"));
    }

    private MvcResult getMvcResult(String uri) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }
}