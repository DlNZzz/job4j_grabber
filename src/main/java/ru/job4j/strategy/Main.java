package ru.job4j.strategy;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        Calendar expiryDate = new GregorianCalendar(2022, Calendar.MARCH, 15);
        Calendar createDate = new GregorianCalendar(2021, Calendar.JANUARY, 1);
        Food food = new Meat("MeatM", expiryDate, createDate, 350, 0);

    }
}
