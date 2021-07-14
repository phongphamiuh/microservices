package com.hotel.project.dto;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JsonDateDeserializer extends JsonDeserializer<Date>
{
    // ISO 8601
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException
    {
        try
        {
            return dateFormat.parse(jsonParser.getText());
        }
        catch ( ParseException e)
        {
            throw new JsonParseException("Could not parse date", jsonParser.getCurrentLocation(), e);
        }
    }
}
