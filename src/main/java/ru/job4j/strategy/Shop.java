package ru.job4j.strategy;

public class Shop implements Store {

    private int discount;

    public Shop() {
    }

    public Shop(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean accept(Food food) {
        return false;
    }

    @Override
    public void doOperation(Food food) {

    }

    @Override
    public boolean add(Food food) {
        return false;
    }
}
