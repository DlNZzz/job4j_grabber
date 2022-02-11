package ru.job4j.ood.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParkingCarTest {

    @Test
    public void testParking() {
        Parking parking = new ParkingCar(2, 1);
        assertTrue(parking.add(new Automobile()));
        assertTrue(parking.add(new Automobile()));
        assertTrue(parking.add(new Truck(5)));
    }

    @Test
    public void testParkingTwo() {
        Parking parking = new ParkingCar(2, 1);
        assertTrue(parking.add(new Truck(3)));
        assertTrue(parking.add(new Truck(2)));
    }

    @Ignore
    @Test
    public void testParkingThree() {
        Parking parking = new ParkingCar(2, 1);
        assertTrue(parking.add(new Truck(2)));
        assertFalse(parking.add(new Truck(3)));
    }

    @Ignore
    @Test
    public void testParkingFour() {
        Parking parking = new ParkingCar(2, 1);
        assertTrue(parking.add(new Truck(6)));
        assertTrue(parking.add(new Truck(2)));
        assertFalse(parking.add(new Truck(1)));
    }

    @Ignore
    @Test
    public void testParkingFive() {
        Parking parking = new ParkingCar(5, 0);
        assertTrue(parking.add(new Truck(2)));
        assertTrue(parking.add(new Truck(3)));
        assertFalse(parking.add(new Truck(1)));
    }

    @Ignore
    @Test
    public void testParkingSix() {
        Parking parking = new ParkingCar(2, 321);
        assertTrue(parking.add(new Automobile()));
        assertTrue(parking.add(new Automobile()));
        assertFalse(parking.add(new Automobile()));
    }
}