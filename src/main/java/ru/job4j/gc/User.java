package ru.job4j.gc;

public class User {    //32 + 4 + 8 = 44 + 4 = 48

    private int age;    //4
    private String name;    //32

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", age, name);
    }

    public static void main(String[] args) {
        User user = new User();
    }
}
