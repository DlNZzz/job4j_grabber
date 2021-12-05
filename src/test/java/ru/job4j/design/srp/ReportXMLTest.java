package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

public class ReportXMLTest {

    @Test
    public void generate() {
        MemStore memStore = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee employee = new Employee("A", now, now, 100);
        Employee employee2 = new Employee("B", now, now, 200);
        memStore.add(employee);
        memStore.add(employee2);
        ReportXML reportXML = new ReportXML(memStore);
    }
}