package ru.job4j.ood.isp.menu;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Сходить в магазин", List.of("Купить продукты"), STUB_ACTION, "1.1."
                ),
                menu.select("Сходить в магазин").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Купить продукты",
                        List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1.1."
                ),
                menu.select("Купить продукты").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Покормить собаку", List.of(), STUB_ACTION, "2.1."
                ),
                menu.select("Покормить собаку").get()
        );
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void sout() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void select() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "b", STUB_ACTION);
        menu.add("b", "c", STUB_ACTION);
        menu.add("b", "z", STUB_ACTION);
        menu.add("b", "q", STUB_ACTION);
        menu.add("z", "p", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "z", List.of("p"), STUB_ACTION, "1.1.2."
                ),
                menu.select("z").get()
        );
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }
}