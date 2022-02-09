package ru.job4j.strategy;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private List<Food> trashList = new ArrayList<>();

    @Override
    public List<Food> getData() {
        return new ArrayList<>(trashList);
    }

    @Override
    public void clear() {
        trashList.clear();
    }

    @Override
    public boolean accept(Food food) {
        double condition = getExpirationPercent(food);
        if (condition > 100) {
            return true;
        }
        return false;
    }

    @Override
    public boolean add(Food food) {
        if (accept(food)) {
            trashList.add(food);
            return true;
        }
        return false;
    }

}
