package ru.job4j.strategy;

public class Shop implements Strategy {

    private int discount;

    public Shop() {
    }

    public Shop(int discount) {
        this.discount = discount;
    }

    @Override
    public void doOperation(Food food) {

    }
}
