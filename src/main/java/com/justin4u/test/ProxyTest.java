package com.justin4u.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

public class ProxyTest {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class clazzProxy = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        System.out.println(clazzProxy);
        printConstructors(clazzProxy);
        printMethods(clazzProxy);
        createProxyInstance();
    }

    private static void printMethods(Class clazz) {
        System.out.println("--methods list--");
        Method[] methods = clazz.getMethods();
        System.out.println(getExtentableList(methods));
    }

    private static void printConstructors(Class clazz) {
        System.out.println("--constructor list--");
        Constructor[] constructors = clazz.getConstructors();
        System.out.println(getExtentableList(constructors));
    }

    private static String getExtentableList(Executable[] executables) {
        StringBuilder sb = new StringBuilder();
        for (Executable executable : executables) {
            String name = executable.getName();
            sb.append(name);
            sb.append("(");
            Class[] classes = executable.getParameterTypes();
            for (Class clazz : classes) {
                sb.append(clazz.getName()).append(",");
            }
            if (classes != null && classes.length != 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")\n");
        }
        return sb.toString();
    }

    private static void createProxyInstance() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        Constructor constructor = clazz.getConstructor(InvocationHandler.class);
        Collection proxy1 = (Collection) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });

        Collection proxy2 = (Collection) Proxy.newProxyInstance(Collection.class.getClassLoader(), new Class[]{Collection.class}, new InvocationHandler() {
            ArrayList target = new ArrayList();

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before invoke method:" + method.getName());
                return method.invoke(target, args);
            }
        });

        proxy2.add("aaa");
        proxy2.add("bbb");
        System.out.println(proxy2.size());
        System.out.println(proxy2);
        System.out.println(proxy2.getClass().getName());

        Collection proxy3 = (Collection) MyAdvice.getProxy(new ArrayList<>(), new MyAdvice());
        proxy3.add("justin");
        proxy3.add("wu");
        System.out.println(proxy3.size());
    }


}
