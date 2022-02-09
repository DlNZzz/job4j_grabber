package ru.job4j.strategy;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private List<Store> listStore;
    private List<Food> foods = new ArrayList<>();

    public ControlQuality(List<Store> listStore) {
        this.listStore = listStore;
    }

    public void distribute(Food food) {
        for (Store s : listStore) {
            if (s.accept(food)) {
                s.add(food);
            }
        }
    }

    public void resort() {
        for (Store s : listStore) {
            foods.addAll(s.getData());
            foods.clear();
        }
        for (Food food : foods) {
            distribute(food);
        }
    }
}
