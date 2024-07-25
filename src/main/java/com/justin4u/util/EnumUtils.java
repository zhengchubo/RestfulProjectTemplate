package com.justin4u.util;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * com.justin4u.util
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-08-03</pre>
 */
public class EnumUtils extends org.apache.commons.lang3.EnumUtils {

    public static final <E extends Enum<E>> boolean isValidCodesStr(Class<E> clazz, String idField, String s) {
        if (null == s) {
            return false;
        }
        String[] arr = s.split(",");
        if (null == arr || arr.length <= 0) {
            return false;
        }

        try {
            Enum[] enumConstants = clazz.getEnumConstants();
            List<Object> values = Arrays.stream(enumConstants)
                    .map(item -> {
                        Object v = getProperty(item, idField);
                        if (v == null) {
                            return null;
                        }
                        return String.valueOf(v);
                    })
                    .collect(Collectors.toList());
            return Arrays.stream(arr).allMatch(item -> values.contains(item));
        } catch (Exception ex) {
            return false;
        }
    }

    public static final <E extends Enum<E>> boolean isValidCodeValue(Class<E> clazz, String idField, Object obj) {
        if (Objects.isNull(obj)) {
            return false;
        }

        try {
            Enum[] enumConstants = clazz.getEnumConstants();
            return Arrays.stream(enumConstants).anyMatch(item -> {
                Object v = getProperty(item, idField);
                if (v == null) {
                    return false;
                }
                if (obj instanceof String) {
                    v = String.valueOf(v);
                }
                return Objects.equals(v, obj);
            });
        } catch (Exception ex) {
            return false;
        }
    }

    public static final boolean isStrInOptions(String s, String options) {
        if (null == s) {
            return false;
        }
        String[] arr = s.split(",");
        if (null == arr || arr.length <= 0) {
            return false;
        }

        String[] arrOptions = options.split(",");
        if (null == arrOptions || arrOptions.length <= 0) {
            return false;
        }

        List<String> optionsList = Arrays.asList(arrOptions);
        return Arrays.stream(arr).allMatch(item -> optionsList.contains(item));
    }

    /**
     * 默认枚举类的主键字段名为 code
     * @param clazz
     * @param obj
     * @param <E>
     * @return
     */
    public static final <E extends Enum<E>> E getEnumByFieldValue(Class<E> clazz, Object obj) {
        return getEnumByFieldValue(clazz, "code", obj);
    }

    public static final <E extends Enum<E>> E getEnumByFieldValue(Class<E> clazz, String idField, Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }

        try {
            E[] enumConstants = clazz.getEnumConstants();
            for (E item : enumConstants) {
                Object v = getProperty(item, idField);
                if (v == null) {
                    return null;
                }
                if (obj instanceof String) {
                    v = String.valueOf(v);
                }
                if (Objects.equals(v, obj)) {
                    return item;
                }
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    public static final <E extends Enum<E>> List<E> getEnumListByFieldValue(Class<E> clazz, String idField, Object obj) {
        if (Objects.isNull(obj)) {
            return new ArrayList<>();
        }

        try {
            E[] enumConstants = clazz.getEnumConstants();
            return Arrays.stream(enumConstants).filter(item -> {
                Object v = getProperty(item, idField);
                if (v == null) {
                    return false;
                }
                if (obj instanceof String) {
                    v = String.valueOf(v);
                }
                if (Objects.equals(v, obj)) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public static Object getProperty(Object obj, String propertyName) {
        Object value = null;
        Class clazz = obj.getClass();
        Field field = findField(clazz, propertyName);
        if (null != field) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            field.setAccessible(accessible);
        }
        return value;
    }

    private static Field findField(Class<?> clazz, String name) {
        try {
            return clazz.getField(name);
        } catch (NoSuchFieldException e) {
            return findDeclaredField(clazz, name);
        }
    }

    private static Field findDeclaredField(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException ex) {
            if (clazz.getSuperclass() != null) {
                return findDeclaredField(clazz.getSuperclass(), name);
            }
            return null;
        }
    }
}
