package ru.job4j.cache;

import java.io.File;
import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DirFileCache dirFileCache = null;
        while (true) {
            System.out.print("1. указать кэшируемую директорию"
                    + "\n2. загрузить содержимое файла в кэш"
                    + "\n3. получить содержимое файла из кэша"
                    + "\nSelect: ");
            try {
                int select = Integer.parseInt(scanner.next());
                if (select == 1) {
                    System.out.println("указажите кэшируемую директорию");
                    String dir = scanner.next();
                    if (new File(dir).isDirectory()) {
                        dirFileCache = new DirFileCache(dir);
                        System.out.println("успешно");
                    } else {
                        System.out.println("неуспешно");
                    }
                } else if (select == 2) {
                    System.out.println("указажите название файла");
                    dirFileCache.get(scanner.next());
                } else if (select == 3) {
                    System.out.println("указажите название файла");
                    System.out.println(dirFileCache.get(scanner.next()));
                }
            } catch (NumberFormatException ignore) {
                System.out.println("Введите число от 1 до 3");
            }
        }
    }
}
