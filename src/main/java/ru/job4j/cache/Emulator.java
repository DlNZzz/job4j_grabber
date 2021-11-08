package ru.job4j.cache;

import java.io.File;
import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("укажите кэшируемую директорию");
        String dir = scanner.nextLine();
        File file = new File(dir);
        if (file.isAbsolute()) {
            DirFileCache dirFileCache = new DirFileCache(dir);
            System.out.println(dirFileCache.load(file.getName()));
        } else {
            System.out.println("неуспешно");
        }
    }
}
