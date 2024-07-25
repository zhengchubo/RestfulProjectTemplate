package com.justin4u.test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class GenericTest {
    public static void main(String[] args) throws NoSuchMethodException {
        getParamType();
    }

    /*利用反射获取方法参数的实际参数类型*/
    public static void getParamType() throws NoSuchMethodException {
        Method method = GenericTest.class.getMethod("applyMap", Map.class);
        //获取方法的泛型参数的类型
        Type[] types = method.getGenericParameterTypes();
        System.out.println(types[0]);
        //参数化的类型
        ParameterizedType pType = (ParameterizedType) types[0];
        //原始类型
        System.out.println(pType.getRawType());
        //实际类型参数
        System.out.println(pType.getActualTypeArguments()[0]);
        System.out.println(pType.getActualTypeArguments()[1]);
    }

    public static void applyMap(Map<Integer, String> map) {
    }
}
