package com.klasevich.cms.util;

import java.util.ResourceBundle;

/**
 * @author Tatsiana Klasevich
 * The Message manager for sending email
 */
public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.pagecontent");

    private MessageManager() {
    }

    /**
     * Gets property.
     *
     * @param key the key word
     * @return the property
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
