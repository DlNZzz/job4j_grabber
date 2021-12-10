package ru.job4j.strategy;

import java.util.List;

public class ControlQuality {

    private List<Store> listStore;

    public ControlQuality(List<Store> listStore) {
        this.listStore = listStore;
    }

    public void controlQuality(Store store, Food food) {
        store.doOperation(food);
    }

    public void distribute(Food food) {
        for (Store s : listStore) {
            if (s.accept(food)) {
                controlQuality(s, food);
            }
        }
    }
}
