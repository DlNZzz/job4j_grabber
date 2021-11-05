package ru.job4j.gc;

public class User {

    private int age;
    private String name;

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", age, name);
    }

    public static void main(String[] args) {
        //User user = new User(21, "");
        //User user2 = new User(21, "qqq");
        for (int i = 0; i < 3000; i++) {
            new User(1, "s");
        }
        System.gc();
    }
}
