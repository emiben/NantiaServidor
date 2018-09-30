package com.nantia.model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JsonDateDeserializer extends JsonDeserializer<Date>
{

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException
    {
    	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//, Locale.ENGLISH);
        String dt = jsonParser.getText();
        if (dt == null || dt.trim().length() == 0) {
            return null;
        }
        Date date = null;
        try {
            date = fmt.parse(dt);
        } catch (ParseException e) {
            throw new IOException(e);
        } 
        return date;
    }
}

