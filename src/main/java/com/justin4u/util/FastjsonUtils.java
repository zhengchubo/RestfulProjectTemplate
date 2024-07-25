package com.justin4u.util;

/*
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;
*/

/**
 * com.justin4u.util
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-08-04</pre>
 */
@Deprecated
public class FastjsonUtils {
    /**
     * JSON to map with fast json
     *
     * @param json      input json string
     * @param keyType   the type of key
     * @param valueType the type of the value
     * @param <K>       specific key class
     * @param <V>       specific value class
     * @return a map
     */
    /*
    public static <K, V> Map<K, V> parseToMap(String json,
                                              Class<K> keyType,
                                              Class<V> valueType) {
        return JSON.parseObject(json, new TypeReference<Map<K, V>>(keyType, valueType) {
        });
    }

    public static boolean areEqual(final String json1, final String json2) {
        return areEqual(JSONObject.parseObject(json1), JSONObject.parseObject(json2));
    }

    public static boolean areEqual(final String json1, final JSONObject json2) {
        return areEqual(JSONObject.parseObject(json1), json2);
    }

    public static boolean areEqual(final JSONObject json1, final JSONObject json2) {
        if(json1 == null && json2 == null) {
            return true;
        } else if(json1 == null || json2 == null) {
            return false;
        }

        for(String key : json1.keySet()) {
            if(!json2.containsKey(key)) {
                return false;
            }

            Object json1Value = json1.get(key);
            Object json2Value = json2.get(key);

            if(json1Value instanceof JSONObject) {
                if(!(json2Value instanceof JSONObject)) {
                    return false;
                } else if (!areEqual((JSONObject) json1Value, (JSONObject) json2Value)) {
                    return false;
                }
            } else if(json1Value instanceof JSONArray) {
                if(!(json2Value instanceof JSONArray)) {
                    return false;
                } else if(!JSONArrayEquals((JSONArray) json1Value, (JSONArray) json2Value)) {
                    return false;
                }
            } else if(!json2.get(key).equals(json1.get(key))) {
                return false;
            }
        }

        return true;
    }

    private static boolean JSONArrayEquals(final JSONArray array1, final JSONArray array2) {
        if(array1.size() != array2.size()) {
            return false;
        }

        for(int i = 0; i < array1.size(); i++) {
            Object value1 = array1.get(i);
            Object value2 = array2.get(i);

            if(value1 instanceof JSONObject) {
                if(!(value2 instanceof JSONObject)) {
                    return false;
                } else if (!areEqual((JSONObject) value1, (JSONObject) value2)) {
                    return false;
                }
            } else if(value1 instanceof JSONArray) {
                if(!(value2 instanceof JSONArray)) {
                    return false;
                } else if(!JSONArrayEquals((JSONArray) value1, (JSONArray) value2)) {
                    return false;
                }
            } else if (!value1.equals(value2)) {
                return false;
            }
        }

        for(int i = 0; i < array2.size(); i++) {
            Object value1 = array1.get(i);
            Object value2 = array2.get(i);

            if(value2 instanceof JSONObject) {
                if(!(value1 instanceof JSONObject)) {
                    return false;
                } else if (!areEqual((JSONObject) value2, (JSONObject) value1)) {
                    return false;
                }
            } else if(value2 instanceof JSONArray) {
                if(!(value1 instanceof JSONArray)) {
                    return false;
                } else if(!JSONArrayEquals((JSONArray) value2, (JSONArray) value1)) {
                    return false;
                }
            } else if (!value2.equals(value1)) {
                return false;
            }
        }

        return true;
    }

    public static String object2String(Object obj) {
        return JSONObject.toJSONString(obj);
    }

    public static JSONObject string2JsonObject(String str) {
        return JSONObject.parseObject(str);
    }

    public static <T> Object string2JavaObject(String str, Class<T> klass) {
        return JSONObject.parseObject(str, klass);
    }

    public static JSONObject javaObject2JsonObject(Object obj) {
        return (JSONObject) JSON.toJSON(obj);
    }

    public static final <T> T evalJsonValueByKey(String jsonStr, String keyStr, T defaultValue) {
        try {
            JSONObject jo = JSONObject.parseObject(jsonStr);
            return evalJsonValueByKey(jo, keyStr, defaultValue);
        } catch (JSONException ex) {
            return defaultValue;
        }
    }

    public static final <T> T evalJsonValueByKey(JSONObject jo, String keyStr, T defaultValue) {
        try {
            Object valueObj = JSONPath.eval(jo, keyStr);
            if (null == valueObj) {
                return defaultValue;
            }
            return (T)valueObj;
        } catch (JSONException ex) {
            return defaultValue;
        }
    }
    */

}
