package com.klasevich.cms.model.factory;

import java.util.Map;

/**
 * @author Tatsiana Klasevich
 * Class used for creating objects with its validation.
 *
 * @param <T> the type of class, which object should be created.
 */
abstract public class AbstractFactory<T> {
    /**
     * Create object of the given type from the Map of its fields.
     *
     * @param data Map with object's fields with RequestParameter's keys.
     * @return object of the given type from the Map of its fields.
     */
    abstract public T create(Map<String, String> data);
}
