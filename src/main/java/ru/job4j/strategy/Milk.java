package ru.job4j.strategy;

import java.util.Calendar;

public class Milk extends Food {
    public Milk(String name, Calendar expiryDate, Calendar createDate, int price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
