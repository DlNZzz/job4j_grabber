package ru.job4j.strategy;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private List<Food> warehouseList = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        double condition = getExpirationPercent(food);
        if (condition < 25) {
            return true;
        }
        return false;
    }

    @Override
    public void doOperation(Food food) {
        if (accept(food)) {
            add(food);
        }
    }

    @Override
    public boolean add(Food food) {
        warehouseList.add(food);
        return false;
    }
}
