package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingCar implements Parking {

    private int sizeParkAuto;
    private int sizeParkTruck;
    private List<Integer> listParkAuto;
    private List<Integer> listParkTruck;

    public ParkingCar(int sizeParkAuto, int sizeParkTruck) {
        this.sizeParkAuto = sizeParkAuto;
        this.sizeParkTruck = sizeParkTruck;
        this.listParkAuto = new ArrayList<>(sizeParkAuto);
        this.listParkTruck = new ArrayList<>(sizeParkTruck);
    }

    @Override
    public boolean add(Car car) {
        return true;
    }

    @Override
    public boolean accept(Car car) {
        return false;
    }
}
