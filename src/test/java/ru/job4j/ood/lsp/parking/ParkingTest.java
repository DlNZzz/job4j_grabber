package ru.job4j.ood.lsp.parking;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ParkingTest {

    @Test
    public void testParking() {
        Parking parking = new ParkingCar(2, 1);
        assertTrue(parking.add(new Automobile()));
        assertTrue(parking.add(new Automobile()));
        assertTrue(parking.add(new Truck(2213)));
    }

    @Test
    public void testParkingTwo() {
        Parking parking = new ParkingCar(2, 1);
        assertTrue(parking.add(new Truck(312123)));
        assertTrue(parking.add(new Truck(2)));
    }
}