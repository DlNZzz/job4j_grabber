package ru.job4j.ood.isp.menu;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.job4j.ood.isp.menu.SimpleMenuTest.STUB_ACTION;

public class PrintTest {

    @Test
    public void print() {
        Menu menu = new SimpleMenu();
        Print print = new Print();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        print.print(menu);
    }
}