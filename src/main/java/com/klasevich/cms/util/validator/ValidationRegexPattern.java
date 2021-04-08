package com.klasevich.cms.util.validator;

import java.util.regex.Pattern;

public class ValidationRegexPattern {
    private ValidationRegexPattern() {
    }

    public static final Pattern PROBLEM_REGEX = Pattern.compile("([\\dА-Яа-я, -.]+){2,100}");
    public static final Pattern CATEGORY_NAME_REGEX = Pattern.compile("[а-яА-Яa-zA-Z]+");
    public static final Pattern SERVICE_NAME_REGEX = Pattern.compile("([а-яА-Яa-z, A-Z]+){1,125}");
    public static final Pattern PRICE_REGEX = Pattern.compile("\\d{1,3}\\.?\\d{0,2}?");
    public static final Pattern NAME_REGEX = Pattern.compile("([а-яА-Яa-z A-Z]+){2,30}");
    public static final Pattern SURNAME_REGEX = Pattern.compile("([а-яА-Яa-z A-Z]+){2,30}");
    public static final Pattern MAIL_REGEX = Pattern.compile("[-\\w]{4,30}@[a-zA-Z]+\\.[a-zA-Z]{2,3}");
    public static final Pattern PHONE_REGEX = Pattern.compile("\\+375(44|33|25|29)\\d{7}");
    public static final Pattern PASS_REGEX = Pattern.compile("\\w{8,20}");
    public static final Pattern DATE_REGEX = Pattern.compile("[2][\\d]{3}[.-](0[1-9]|1[012])[.-](0[1-9]|1[0-9]|2[0-9]|3[01])");
    public static final Pattern ADDRESS_REGEX = Pattern.compile("([\\dА-Яа-я, -.]+){2,100}");
    public static final Pattern ID_REGEX = Pattern.compile("\\d+");
    public static final String SEPARATOR_BEFORE = "\\\\";
    public static final String SEPARATOR_AFTER = "/";
    public static final String SEPARATOR_NEW = "//";
    public static final String FIRST_SEPARATOR="-";
    public static final String SECOND_SEPARATOR=".";
    public static final Pattern PAGE_REGEX = Pattern.compile("(/[0-9a-z_]+)(/[a-z_]+/[a-z_]+\\.jsp)");
}
