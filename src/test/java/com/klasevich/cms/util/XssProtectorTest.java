package com.klasevich.cms.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * The type Xss protector test.
 */
public class XssProtectorTest {
    /**
     * Data for xss protector object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider
    public Object[][] dataForXssProtector() {
        return new Object[][]{
                {"<script>andy</script>", "andy"},
                {"<script>tommy<script>", "tommy"},
                {"gleb</script>", "gleb"},
                {"<script>andy_sheriff", "andy_sheriff"},
                {"bruno", "bruno"},
        };
    }

    /**
     * Protect.
     *
     * @param line     the line
     * @param expected the expected
     */
    @Test(dataProvider = "dataForXssProtector")
    public void protect(String line, String expected) {
        String actual = XssProtector.protect(line);
        assertEquals(actual, expected);
    }
}