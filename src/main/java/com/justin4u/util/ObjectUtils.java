package com.justin4u.util;

import java.lang.reflect.Field;

/**
 * com.justin4u.util
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-08-03</pre>
 */
public class ObjectUtils {

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

    public static Field findDeclaredField(Class<?> clazz, String name) {
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
