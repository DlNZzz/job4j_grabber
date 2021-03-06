package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class Print implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> menuItemInfoIterator = menu.iterator();
        while (menuItemInfoIterator.hasNext()) {
            StringBuilder stringBuilder = new StringBuilder();
            Menu.MenuItemInfo menuItemInfo = menuItemInfoIterator.next();
            int num = menuItemInfo.getNumber().length() / 2;
            stringBuilder.append("----".repeat(num));
            System.out.println(stringBuilder
                    + " " + menuItemInfo.getNumber() + menuItemInfo.getName());
        }
    }
}
