package ru.job4j.ood.lsp;

public class Square extends Rectangle {

    @Override
    public int getWidth() {
        return getLength();
    }

    public static void main(String[] args) throws Exception {
        Rectangle rectangle = new Square();
        testRectangle(rectangle);
    }

    public static void testRectangle(Rectangle rectangle) throws Exception {
        rectangle.setLength(10);
        System.out.println(rectangle.area());
    }
}
