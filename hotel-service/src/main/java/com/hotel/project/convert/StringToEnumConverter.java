package com.hotel.project.convert;


import com.hotel.project.model.entity.StarType;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, StarType> {

    @Override
    public StarType convert(String source) {
        try {
            return StarType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
