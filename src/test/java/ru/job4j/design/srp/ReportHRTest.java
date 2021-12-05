package ru.job4j.design.srp;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReportHRTest {

    @Test
    public void whenOldGenerated() throws JAXBException {
        String newLine = System.lineSeparator();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("A", now, now, 300);
        Employee worker2 = new Employee("Ivan", now, now, 100);
        Employee worker3 = new Employee("A", now, now, 200);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(newLine)
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(newLine)
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(newLine)
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(newLine);
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}