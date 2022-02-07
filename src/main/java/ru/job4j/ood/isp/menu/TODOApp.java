package ru.job4j.ood.isp.menu;

import javax.swing.text.html.Option;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class TODOApp {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Menu menu = new SimpleMenu();
        boolean isEmpty = true;
        while (isEmpty) {
            System.out.println("1. добавить");
            System.out.println("2. выбрать по названию");
            System.out.println("3. вывести меню");
            System.out.println("4. выход");
            String select = bufferedReader.readLine();
            switch (select) {
                case ("1"):
                    System.out.println("Введите название родителя");
                    String parent = bufferedReader.readLine();
                    System.out.println("Введите название ребенка");
                    String child = bufferedReader.readLine();
                    boolean b = menu.add(parent, child, STUB_ACTION);
                    if (b) {
                        System.out.println("успешно");
                    } else {
                        System.out.println("неуспешно");
                    }
                    break;
                case ("2"):
                    System.out.println("Введите название родителя");
                    parent = bufferedReader.readLine();
                    Optional<Menu.MenuItemInfo> option = menu.select(parent);
                    if (option.isPresent()) {
                        Menu.MenuItemInfo menuItemInfo = option.get();
                        System.out.println(menuItemInfo.getNumber() + " " + menuItemInfo.getName());
                    } else {
                        System.out.println("неуспешно");
                    }
                    break;
                case ("3"):
                    Print print = new Print();
                    print.print(menu);
                    break;
                case ("4"):
                    isEmpty = false;
                    break;
            }
        }
    }
}
