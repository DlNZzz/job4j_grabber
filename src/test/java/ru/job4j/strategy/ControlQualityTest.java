package ru.job4j.strategy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ControlQualityTest {

    @Test
    public void distribute() {
        List<Store> list = new ArrayList<>();
        list.add(new Warehouse());
        list.add(new Shop());
        list.add(new Trash());
        ControlQuality controlQuality = new ControlQuality(list);
        Calendar expiryDate = new GregorianCalendar(2022, Calendar.MARCH, 15);
        Calendar createDate = new GregorianCalendar(2021, Calendar.JANUARY, 1);
        controlQuality.distribute(new Meat("MeatM", expiryDate, createDate, 350, 0));
    }
}