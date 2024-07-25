package com.justin4u.util;

import org.springframework.core.convert.converter.Converter;

public class NumberToStringConverter implements Converter<Number, String> {
    @Override
    public String convert(Number source) {
        if (source != null) {
            return source.toString();
        }
        return null;
    }
}
