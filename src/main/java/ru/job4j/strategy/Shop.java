package ru.job4j.strategy;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private int discount;
    private List<Food> shopList = new ArrayList<>();

    public Shop() {
    }

    public Shop(int discount) {
        this.discount = discount;
    }

    @Override
    public List<Food> getData() {
        return shopList;
    }

    @Override
    public boolean accept(Food food) {
        double condition = getExpirationPercent(food);
        if (condition >= 25 & condition < 100) {
            return true;
        }
        return false;
    }

    public Food discount(Food food, int discount) {
        return food.setDiscount(discount);
    }

    @Override
    public boolean add(Food food) {
        if (accept(food)) {
            if (getExpirationPercent(food) > 75) {
                shopList.add(discount(food, discount));
            } else {
                shopList.add(food);
            }
            return true;
        }
        return false;
    }
}


