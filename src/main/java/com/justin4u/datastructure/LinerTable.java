package com.justin4u.datastructure;

public class LinerTable {

    private int[] data;

    public int[] expand(int size) {
        int[] to = new int[size];
        if (data == null || data.length <= 0) {
            return to;
        }

        for (int i = 0; i < data.length; i++) {
            to[i] = data[i];
        }
        return to;
    }

    public void add(int index, int element) throws Exception {
        if (index < 0 || index > data.length) {
            throw new Exception("Invalid index location");
        }
        if (index >= data.length) {
            expand(index - data.length);
        }
        data[index] = element;
    }
}
