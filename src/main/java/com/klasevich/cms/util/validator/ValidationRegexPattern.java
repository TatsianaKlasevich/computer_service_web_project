package com.klasevich.cms.util.validator;

import java.util.regex.Pattern;

/**
 * The class includes validation regex patterns.
 */
public class ValidationRegexPattern {
    private ValidationRegexPattern() {
    }

    /**
     * The constant PROBLEM_REGEX.
     */
    public static final Pattern PROBLEM_REGEX = Pattern.compile("([\\dА-Яа-я, -.]+){2,100}");
    /**
     * The constant CATEGORY_NAME_REGEX.
     */
    public static final Pattern CATEGORY_NAME_REGEX = Pattern.compile("[а-яА-Яa-zA-Z]+");
    /**
     * The constant SERVICE_NAME_REGEX.
     */
    public static final Pattern SERVICE_NAME_REGEX = Pattern.compile("([а-яА-Я, ]+){1,125}");
    /**
     * The constant PRICE_REGEX.
     */
    public static final Pattern PRICE_REGEX = Pattern.compile("\\d{1,3}\\.?\\d{0,2}?");
    /**
     * The constant NAME_REGEX.
     */
    public static final Pattern NAME_REGEX = Pattern.compile("([а-яА-Яa-z A-Z]+){2,30}");
    /**
     * The constant SURNAME_REGEX.
     */
    public static final Pattern SURNAME_REGEX = Pattern.compile("([а-яА-Яa-z A-Z]+){2,30}");
    /**
     * The constant MAIL_REGEX.
     */
    public static final Pattern MAIL_REGEX = Pattern.compile("[-\\w]{4,30}@[a-zA-Z]+\\.[a-zA-Z]{2,3}");
    /**
     * The constant PHONE_REGEX.
     */
    public static final Pattern PHONE_REGEX = Pattern.compile("\\+375(44|33|25|29)\\d{7}");
    /**
     * The constant PASS_REGEX.
     */
    public static final Pattern PASS_REGEX = Pattern.compile("([\\w\\p{Punct}]+){8,20}");
    /**
     * The constant DATE_REGEX.
     */
    public static final Pattern DATE_REGEX = Pattern.compile("[2][\\d]{3}[.-](0[1-9]|1[012])[.-](0[1-9]|1[0-9]|2[0-9]|3[01])");
    /**
     * The constant ADDRESS_REGEX.
     */
    public static final Pattern ADDRESS_REGEX = Pattern.compile("([\\dА-Яа-я, -.]+){2,100}");
    /**
     * The constant ID_REGEX.
     */
    public static final Pattern ID_REGEX = Pattern.compile("\\d+");
    /**
     * The constant SEPARATOR_BEFORE.
     */
    public static final String SEPARATOR_BEFORE = "\\\\";
    /**
     * The constant SEPARATOR_AFTER.
     */
    public static final String SEPARATOR_AFTER = "/";
    /**
     * The constant SEPARATOR_NEW.
     */
    public static final String SEPARATOR_NEW = "//";
    /**
     * The constant FIRST_SEPARATOR.
     */
    public static final String FIRST_SEPARATOR = "-";
    /**
     * The constant SECOND_SEPARATOR.
     */
    public static final String SECOND_SEPARATOR = ".";
    /**
     * The constant PAGE_REGEX.
     */
    public static final Pattern PAGE_REGEX = Pattern.compile("(/[0-9a-z_]+)(/[a-z_]+/[a-z_]+\\.jsp)");
}
