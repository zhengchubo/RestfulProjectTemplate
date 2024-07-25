package com.justin4u.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.json.JsonParseException;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * com.justin4u.util
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-08-11</pre>
 */
public class JacksonUtils {
    /**
     * 单例内部类
     */
    private static class SingletonHolder {
        private static final ObjectMapper mapper = new ObjectMapper();
        private static Configuration jsonPathConf = null;
        static {
            //Jackson 支持org.json.JSONObject
            mapper.registerModule(new JsonOrgModule());
            //Jackson 反序列化配置
            // 属性在json有, entity有, 但标记为ignore注解, 不抛出异常
            mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            // 属性在json有, entity没有,  不抛出异常
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            // 支持json中的key无双引号
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            // 支持带单引号的key
            mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            // 支持0开头的整数, 如001
            mapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
            // 支持回车符号
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            // int类型为null, 则抛出异常
            mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
            // 枚举找不到值, 不抛出异常
            mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
            //Jackson 序列化配置
            // 空值输出 字段名: null
            mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
            // transient注解不输出字段
            mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
            //JsonPath
            Configuration.setDefaults(new Configuration.Defaults() {
                private final JsonProvider jsonProvider = new JacksonJsonProvider(mapper);
                private final MappingProvider mappingProvider = new JacksonMappingProvider(mapper);
                @Override
                public JsonProvider jsonProvider() {
                    return jsonProvider;
                }
                @Override
                public MappingProvider mappingProvider() {
                    return mappingProvider;
                }
                @Override
                public Set<Option> options() {
                    return EnumSet.noneOf(Option.class);
                }
            });
            jsonPathConf = Configuration.defaultConfiguration()
                    .addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL)
                    .addOptions(Option.SUPPRESS_EXCEPTIONS)
            ;
            //others
        }
    }
    /**
     * 单例get
     * @return
     */
    public static ObjectMapper getInstance(){
        return SingletonHolder.mapper;
    }
    /**
     * clone 一个可以改配置
     * @return
     */
    public static ObjectMapper copyInstance(){
        ObjectMapper copy = SingletonHolder.mapper.copy();
        return copy;
    }
    /**
     * 指定Obj 对象-> 字符串
     * @param obj
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> String toJSONString(T obj) {
        try {
            return getInstance().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
    /**
     * 字符串 -> 指定Obj 对象
     * @param json
     * @param valueType
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T parseObject(String json, Class<T> valueType) throws IOException {
        return getInstance().readValue(json, valueType);
    }
    /**
     * 字符串 -> 通用类JSONObject
     * @param json
     * @return
     * @throws IOException
     */
    public static JSONObject parseObject(String json) throws IOException {
        return getInstance().readValue(json, JSONObject.class);
    }
    /**
     * 字符串 -> 指定数组类
     * @param json
     * @param valueType
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> List<T> parseArray(String json, Class<T> valueType) throws IOException {
        return getInstance().readValue(json, new TypeReference<List<T>>(){});
    }
    /**
     * 字符串 -> 通用数组类 JSONArray
     * @param json
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> JSONArray parseArray(String json) throws IOException {
        return getInstance().readValue(json, JSONArray.class);
    }
    /**
     * 高级: 获取JSONPath 帮助类
     * @param jsonStr
     * @return
     */
    public static DocumentContext getDocumentContext(String jsonStr) {
        DocumentContext dcHelp = JsonPath.using(SingletonHolder.jsonPathConf).parse(jsonStr);
        return dcHelp;
    }
    /**
     * 高级: 解析 JSONPath
     * @param dcHelp
     * @param path
     * @return
     */
    public static <T> T evalPath(DocumentContext dcHelp, String path) {
        // 参考链接 https://github.com/json-path/JsonPath
        return dcHelp.read(path);
    }
    /**
     * 高级: 默认配置解析 JSONPath
     * @param jsonStr
     * @param path
     * @return
     */
    public static String evalPath(String jsonStr, String path, String defaultValue) {
        DocumentContext dcHelp = getDocumentContext(jsonStr);
        Object v = evalPath(dcHelp, path);
        if (Objects.isNull(v)) {
            return defaultValue;
        }
        if (v instanceof String) {
            return (String) v;
        } else {
            return v.toString();
        }
    }

    /**
     * 判断参数是否为合法的 JSON 串。
     * @param s
     * @return
     */
    public static final boolean isJsonString(String s) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return false;
        }
        try {
            getInstance().readTree(s);
        } catch (JsonProcessingException e) {
            return false;
        }
        return true;
    }
    public static <T> T tryParse(Callable<T> parser) {
        return tryParse(parser, JacksonException.class);
    }

    public static <T> T tryParse(Callable<T> parser, Class<? extends Exception> check) {
        try {
            return parser.call();
        } catch (Exception ex) {
            if (check.isAssignableFrom(ex.getClass())) {
                throw new JsonParseException(ex);
            }
            throw new IllegalStateException(ex);
        }
    }
}
