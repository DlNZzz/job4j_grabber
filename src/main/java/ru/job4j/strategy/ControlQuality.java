package ru.job4j.strategy;

public class ControlQuality {

    private Strategy strategy;

    public ControlQuality(Strategy strategy) {
        this.strategy = strategy;
    }

    public void controlQuality(Food food) {
        strategy.doOperation(food);
    }
}
