package com.klasevich.cms.util.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;

import static com.klasevich.cms.util.validator.ValidationRegexPattern.PAGE_REGEX;

public class PageValidator {
    private static Logger logger = LogManager.getLogger();

    public static String findPage(String page) {
        Matcher matcher = PAGE_REGEX.matcher(page);
        String currentPage = null;
        if (matcher.matches()) {
            currentPage = matcher.group(2);
        }
        logger.debug("page validator {}", currentPage);
        return currentPage;
    }
}
