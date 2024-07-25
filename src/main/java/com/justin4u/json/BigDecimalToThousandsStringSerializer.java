package com.justin4u.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * BigDecimal 转为带千分位的字符串，保留 2 位小数。
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-09-18</pre>
 */
public class BigDecimalToThousandsStringSerializer extends ToStringSerializer {

    public static final BigDecimalToThousandsStringSerializer instance = new BigDecimalToThousandsStringSerializer();

    public BigDecimalToThousandsStringSerializer() {
    }

    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value instanceof BigDecimal) {
            String writeString = this.formatThousandDelimiterWithPrecision2((BigDecimal)value);
            gen.writeString(writeString);
        } else if (value instanceof String && !StringUtils.isEmpty((String)value) && NumberUtils.isCreatable((String)value)) {
            BigDecimal decimalValue = new BigDecimal((String)value);
            String writeString = this.formatThousandDelimiterWithPrecision2(decimalValue);
            gen.writeString(writeString);
        } else {
            super.serialize(value, gen, provider);
        }

    }

    private String formatThousandDelimiterWithPrecision2(BigDecimal decimal) {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format(decimal);
    }

    public void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        this.serialize(value, gen, provider);
    }
}
