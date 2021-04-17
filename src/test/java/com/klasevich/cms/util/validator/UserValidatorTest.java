package com.klasevich.cms.util.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.klasevich.cms.command.command_parameter.RequestParameter.*;
import static com.klasevich.cms.command.command_parameter.RequestParameter.PARAM_RE_PASSWORD;
import static org.testng.Assert.*;

/**
 * The type User validator test.
 */
public class UserValidatorTest {
    /**
     * Data for valid registration object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider
    public Object[][] dataForValidRegistration() {
        Map<String, String> correctData = new HashMap<>();
        correctData.put(PARAM_NAME, "Robinson");
        correctData.put(PARAM_SURNAME, "Cruso");
        correctData.put(PARAM_MAIL, "robinson@gmail.com");
        correctData.put(PARAM_PHONE, "+375331123432");
        correctData.put(PARAM_PASSWORD, "12345678");
        correctData.put(PARAM_RE_PASSWORD, "12345678");

        Map<String, String> incorrectData = new HashMap<>();
        incorrectData.put(PARAM_NAME, "A");
        incorrectData.put(PARAM_SURNAME, "Cruso");
        incorrectData.put(PARAM_MAIL, "ro@gmail.com");
        incorrectData.put(PARAM_PHONE, "+3753311234322");
        incorrectData.put(PARAM_PASSWORD, "12345678");
        incorrectData.put(PARAM_RE_PASSWORD, "1234sdf5678");

        return new Object[][]{
                {correctData, true},
                {incorrectData, false},
        };
    }

    /**
     * Test is valid registration.
     *
     * @param data     the data
     * @param expected the expected
     */
    @Test(dataProvider = "dataForValidRegistration")
    public void testIsValidRegistration(Map<String, String> data, boolean expected) {
        boolean actual = UserValidator.isValidRegistration(data);
        assertEquals(actual, expected);
    }
}