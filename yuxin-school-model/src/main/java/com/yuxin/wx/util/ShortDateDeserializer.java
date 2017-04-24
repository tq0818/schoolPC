package com.yuxin.wx.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class ShortDateDeserializer extends JsonDeserializer<Date>{
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
    @Override  
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {  
        try {  
        	System.out.println(jsonParser.getText());
            return format.parse(jsonParser.getText());  
        } catch (ParseException e) {  
            throw new RuntimeException(e);  
        }  
    }  
}
