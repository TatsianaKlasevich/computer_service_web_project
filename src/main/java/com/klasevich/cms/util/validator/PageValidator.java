package com.klasevich.cms.util.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;

import static com.klasevich.cms.util.validator.ValidationRegexPattern.PAGE_REGEX;

/**
 * Class check if page is valid.
 */
public class PageValidator {
    private static Logger logger = LogManager.getLogger();

    /**
     * Change page.
     *
     * @param page the page
     * @return changed page
     */
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
