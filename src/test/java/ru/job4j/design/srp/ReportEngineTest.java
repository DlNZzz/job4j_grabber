package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<tr>Name; Salary;</tr>")
                .append(System.lineSeparator() + "<tr>")
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append("</tr>" + System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenOldGeneratedTwo() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Q", now, now, 200);
        Employee worker3 = new Employee("W", now, now, 300);
        store.add(worker2);
        store.add(worker);
        store.add(worker3);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<tr>Name; Salary;</tr>")
                .append(System.lineSeparator() + "<tr>")
                .append(worker3.getName())
                .append(worker3.getSalary() * 0.87)
                .append("</tr>" + System.lineSeparator())
                .append(System.lineSeparator() + "<tr>")
                .append(worker2.getName())
                .append(worker2.getSalary() * 0.87)
                .append("</tr>" + System.lineSeparator())
                .append(System.lineSeparator() + "<tr>")
                .append(worker.getName())
                .append(worker.getSalary() * 0.87)
                .append("</tr>" + System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}