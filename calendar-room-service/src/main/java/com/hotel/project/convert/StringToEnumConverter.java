package com.hotel.project.convert;


import com.hotel.project.model.RoomOption;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, RoomOption> {

    @Override
    public RoomOption convert(String source) {
        try {
            return RoomOption.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
