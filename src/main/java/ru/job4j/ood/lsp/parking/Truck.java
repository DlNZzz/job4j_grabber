package ru.job4j.ood.lsp.parking;

public class Truck implements Car {

    private static int size;

    public Truck(int size) {
        Truck.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
