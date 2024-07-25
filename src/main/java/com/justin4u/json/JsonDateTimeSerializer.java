package com.justin4u.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * com.justin4u.json
 * 线程安全地将Date字段格式为JSON，使用系统默认的时区。
 * usage:
 * <pre>
 *     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 *     @JsonSerialize(using = JsonDateTimeSerializer.class)
 *     private Date date;
 * </pre>
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-03-22</pre>
 */
public class JsonDateTimeSerializer extends ToStringSerializer {
    private String pattern = "yyyy-MM-dd HH:mm:ss";

    public JsonDateTimeSerializer(String pattern) {
        this.pattern = pattern;
    }

    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        if (null == date) {
            gen.writeNull();
            return;
        }

        String text = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern(pattern));

        gen.writeString(text);
    }

}
