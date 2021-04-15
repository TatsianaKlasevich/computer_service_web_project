package com.klasevich.cms.util.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.klasevich.cms.command.command_parameter.RequestParameter.*;
import static org.testng.Assert.*;

public class OrderValidatorTest {
    @DataProvider
    public Object[][] dataForValidOrder() {
        Map<String, String> correctData = new HashMap<>();
        correctData.put(DATE, "2021.04.15");
        correctData.put(ADDRESS, "ул.Слободская, 7-89");
        correctData.put(CATEGORY_NAME, "LAPTOP");
        correctData.put(PROBLEM, "не работает");

        Map<String, String> incorrectData = new HashMap<>();
        incorrectData.put(DATE, "1018.04.15");
        incorrectData.put(ADDRESS, "ул.Слободская,street 7-89");
        incorrectData.put(CATEGORY_NAME, "1laptop");
        incorrectData.put(PROBLEM, "не работает, don't work");

        return new Object[][]{
                {correctData, true},
                {incorrectData, false},
        };
    }

    @Test(dataProvider = "dataForValidOrder")
    public void testIsValidOrder(Map<String, String> data, boolean expected) {
        boolean actual = OrderValidator.isValidOrder(data);
        assertEquals(actual, expected);
    }
}
