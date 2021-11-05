package ru.job4j.gc;

public class GCDemoUser {
    public static void main(String[] args) {
        for (int i = 0; i < 56000; i++) {
            new User(i, "qqq");
        }
    }
}
