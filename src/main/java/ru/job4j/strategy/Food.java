package ru.job4j.strategy;

import java.util.Calendar;

public class Food {

    private String name;
    private Calendar expiryDate;
    private Calendar createDate;
    private int price;
    private int discount;

    public Food(String name, Calendar expiryDate, Calendar createDate, int price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public Food setName(String name) {
        this.name = name;
        return this;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public Food setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public Food setCreateDate(Calendar createDate) {
        this.createDate = createDate;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Food setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getDiscount() {
        return discount;
    }

    public Food setDiscount(int discount) {
        this.discount = discount;
        return this;
    }
}
