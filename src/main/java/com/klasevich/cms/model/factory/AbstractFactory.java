package com.klasevich.cms.model.factory;

import java.util.Map;

abstract public class AbstractFactory<T> {
   abstract public T create(Map<String,String> data);
}
