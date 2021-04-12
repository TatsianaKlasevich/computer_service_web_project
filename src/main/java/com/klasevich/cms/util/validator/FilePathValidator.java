package com.klasevich.cms.util.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.klasevich.cms.util.validator.ValidationRegexPattern.*;

public class FilePathValidator {
    private static final Logger logger = LogManager.getLogger();

    public static String findPath(String path) {
        String resultPath = path.replaceAll(SEPARATOR_BEFORE, SEPARATOR_AFTER);
        logger.debug(" resultPath {}", resultPath);
        String result = resultPath.replaceAll(SEPARATOR_NEW, SEPARATOR_AFTER);
        logger.debug(" result{}", result);
        return result;
    }
}
