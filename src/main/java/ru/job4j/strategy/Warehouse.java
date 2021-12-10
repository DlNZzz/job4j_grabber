package ru.job4j.strategy;

public class Warehouse implements Store {

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
