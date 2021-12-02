package ru.job4j.ood.lsp;

public class Rectangle {

    private int length;
    private int width;

    public int getLength() {
        return length;
    }

    public Rectangle setLength(int length) throws Exception {
        if (length <= 0) {
            throw new Exception();
        }
        this.length = length;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public Rectangle setWidth(int width) throws Exception {
        if (width <= 0) {
            throw new Exception();
        }
        this.width = width;
        return this;
    }

    public int area() {
        return length * width;
    }
}
