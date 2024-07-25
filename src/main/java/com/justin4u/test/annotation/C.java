package com.justin4u.test.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class C {
    public static void main(String[] args) throws ClassNotFoundException {
        parseTypeAnnotation();
        parseContructorAnnotation();
        parseMethodAnnotation();
    }

    public static void parseTypeAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("com.justin4u.test.annotation.B");
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            A a = (A) annotation;
            System.out.println("id=" + a.id() + "; name=" + a.name() + "; gid=" + a.gid());
        }
    }

    public static void parseMethodAnnotation() {
        Method[] methods = B.class.getDeclaredMethods();
        for (Method method : methods) {
            boolean hasAnnotation = method.isAnnotationPresent(A.class);
            if (hasAnnotation) {
                A a = method.getAnnotation(A.class);
                System.out.println("method=" + method.getName() + "; id=" + a.id() + "; name=" + a.name() + "; gid=" + a.gid());
            }
        }
    }

    public static void parseContructorAnnotation() {
        Constructor[] constructors = B.class.getConstructors();
        for (Constructor constructor : constructors) {
            boolean hasAnnotation = constructor.isAnnotationPresent(A.class);
            if (hasAnnotation) {
                A a = (A) constructor.getAnnotation(A.class);
                System.out.println("constructor=" + constructor.getName() + "; id=" + a.id() + "; name=" + a.name() + "; gid=" + a.gid());
            }
        }
    }

}
