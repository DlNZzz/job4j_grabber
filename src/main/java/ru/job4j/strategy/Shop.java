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
        double condition = getExpirationPercent(food);
        if (condition >= 25 & condition < 100) {
            return true;
        }
        return false;
    }

    @Override
    public void doOperation(Food food) {
        if (accept(food)) {
            if (getExpirationPercent(food) > 75) {
                add(discount(food, discount));
            } else {
                add(food);
            }
        }
    }

    public Food discount(Food food, int discount) {
        return food.setDiscount(discount);
    }

    @Override
    public boolean add(Food food) {

        return false;
    }
}
