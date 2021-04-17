package com.klasevich.cms.util;

/**
 * @author Tatsiana Klasevich
 * Class for making Xss protecting.
 */
public class XssProtector {
    private static final String EMPTY = "";
    private static final String SCRIPT_REGEX = "</?script>";

    /**
     * Protect XSS.
     *
     * @param line the String line for checking and change if it has script
     * @return the line without script
     */
    public static String protect(String line) {
        String securedLine = line;
        if (line != null) {
            securedLine = line.replaceAll(SCRIPT_REGEX, EMPTY);
        }
        return securedLine;
    }
}
