package com.yuxin.wx.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 短日期格式JSON日期:yyyy-MM-dd
 * 
 * @author wang.zx
 * @date 2013-02-26
 */
public class ShortDateSerializer extends JsonSerializer<Date> {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        String formattedDate = formatter.format(value);
        jgen.writeString(formattedDate);
    }

}