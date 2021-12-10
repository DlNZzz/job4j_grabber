package ru.job4j.strategy;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private List<Food> trashList = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        double condition = getExpirationPercent(food);
        if (condition > 100) {
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
        trashList.add(food);
        return false;
    }
}
