package com.justin4u.designpattern.singleton;

/**
 * Double check lock
 */
public class DclSingleton {
    private volatile static DclSingleton singleton;

    private DclSingleton() {
    }

    public static DclSingleton getSingleton() {
        if (singleton == null) {
            synchronized (DclSingleton.class) {
                if (singleton == null) {
                    singleton = new DclSingleton();
                }
            }
        }
        return singleton;
    }
}
