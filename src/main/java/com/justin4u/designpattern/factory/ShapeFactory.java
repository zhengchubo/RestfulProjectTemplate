package com.justin4u.designpattern.factory;

public class ShapeFactory {
    public static void main(String[] args) {
        ShapeFactory instance = new ShapeFactory();
        Shape shape1 = instance.getShape("CIRCLE");
        shape1.draw();
        Shape shape2 = instance.getShape("RECTANGLE");
        shape2.draw();
        Shape shape3 = instance.getShape("SQUARE");
        shape3.draw();
    }

    public Shape getShape(String type) {
        if (null == type) {
            return null;
        }
        switch (type) {
            case "CIRCLE":
                return new Circle();
            case "RECTANGLE":
                return new Rectangle();
            case "SQUARE":
                return new Square();
            default:
                return null;
        }
    }
}
