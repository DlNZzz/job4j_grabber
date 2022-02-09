package ru.job4j.strategy;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private List<Food> warehouseList = new ArrayList<>();

    @Override
    public List<Food> getData() {
        return new ArrayList<>(warehouseList);
    }

    @Override
    public void clear() {
        warehouseList.clear();
    }

    @Override
    public boolean accept(Food food) {
        double condition = getExpirationPercent(food);
        if (condition < 25) {
            return true;
        }
        return false;
    }

    @Override
    public boolean add(Food food) {
        if (accept(food)) {
            warehouseList.add(food);
            return true;
        }
        return false;
    }
}
