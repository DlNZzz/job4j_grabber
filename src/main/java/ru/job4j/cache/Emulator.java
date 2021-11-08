package ru.job4j.cache;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
- указать кэшируемую директорию

- загрузить содержимое файла в кэш

- получить содержимое файла из кэша
 */
public class Emulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("1. указать кэшируемую директорию"
                    + "\n2. загрузить содержимое файла в кэш"
                    + "\n3. получить содержимое файла из кэша"
                    + "\nSelect: ");
            try {
                int select = scanner.nextInt();
                if (select == 1) {
                    System.out.println("указажите кэшируемую директорию");
                    String dir = scanner.nextLine();
                    if (new File(dir).isFile()) {
                        new DirFileCache(dir);
                        System.out.println("успешно");
                    } else {
                        System.out.println("неуспешно");
                    }
                } else if (select == 2) {

                } else if (select == 3) {

                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        }
    }
}
