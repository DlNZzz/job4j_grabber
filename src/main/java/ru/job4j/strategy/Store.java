package ru.job4j.strategy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public interface Store {

    List<Food> getData();

    boolean accept(Food food);

    boolean add(Food food);

    default double getExpirationPercent(Food food) {
        Calendar createDate = food.getCreateDate();
        Calendar expiryDate = food.getExpiryDate();
        Calendar currentDate = Calendar.getInstance();
        int allDays = (expiryDate.get(Calendar.YEAR) - createDate.get(Calendar.YEAR)) * 365
                + (expiryDate.get(Calendar.MONTH) - createDate.get(Calendar.MONTH)) * 30
                + expiryDate.get(Calendar.DAY_OF_MONTH) - createDate.get(Calendar.DAY_OF_MONTH);
        int days = (currentDate.get(Calendar.YEAR) - createDate.get(Calendar.YEAR)) * 365
                + (currentDate.get(Calendar.MONTH) - createDate.get(Calendar.MONTH)) * 30
                + currentDate.get(Calendar.DAY_OF_MONTH) - createDate.get(Calendar.DAY_OF_MONTH);
        if (days < 0) {
            return 0;
        }
        return days * 100 / allDays;
    }
}
