package ru.job4j.design.srp;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReportXMLTest {

    @Test
    public void generate() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee employee = new Employee("A", now, now, 100);
        store.add(employee);
        Report reportXML = new ReportXML(store);
        StringBuilder expected = new StringBuilder()
                .append("");
        assertThat(reportXML.generate(filter -> true), is(expected.toString()));
    }
}