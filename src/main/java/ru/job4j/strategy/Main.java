package ru.job4j.strategy;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.locks.Condition;

public class Main {

    public static void main(String[] args) {
        Calendar expiryDate = new GregorianCalendar(2022, Calendar.MARCH, 15);
        Calendar createDate = new GregorianCalendar(2021, Calendar.JANUARY, 1);
        Food food = new Meat("MeatM", expiryDate, createDate, 350, 0);
        int condition = quality(expiryDate, createDate);
        System.out.println(condition);
        if (condition >= 25) {
            if (condition < 75) {
                new ControlQuality(new Shop()).controlQuality(food);
                System.out.println("Shop");
            } else {
                new ControlQuality(new Shop(30)).controlQuality(food);
                System.out.println("Shop(30)");
            }
        } else {
            if (condition > 0) {
                new ControlQuality(new Warehouse()).controlQuality(food);
                System.out.println("Warehouse");
            } else {
                new ControlQuality(new Trash()).controlQuality(food);
                System.out.println("Trash");
            }
        }
    }

    public static int quality(Calendar expiryDate, Calendar createDate) {
        Calendar currentDate = Calendar.getInstance();
        int allDays = (expiryDate.get(Calendar.YEAR) - createDate.get(Calendar.YEAR)) * 365
                + (expiryDate.get(Calendar.MONTH) - createDate.get(Calendar.MONTH)) * 30
                + expiryDate.get(Calendar.DAY_OF_MONTH) - createDate.get(Calendar.DAY_OF_MONTH);
        System.out.println(allDays);
        int days = (currentDate.get(Calendar.YEAR) - createDate.get(Calendar.YEAR)) * 365
                + (currentDate.get(Calendar.MONTH) - createDate.get(Calendar.MONTH)) * 30
                + currentDate.get(Calendar.DAY_OF_MONTH) - createDate.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        if (days < 0) {
            return 0;
        }
        return days * 100 / allDays;
    }
}
