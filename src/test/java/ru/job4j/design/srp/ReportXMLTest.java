package ru.job4j.design.srp;

import org.junit.Ignore;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReportXMLTest {

    @Ignore
    @Test
    public void generate() throws JAXBException {
        MemStore store = new MemStore();
        Calendar date = new GregorianCalendar(2020, Calendar.JANUARY, 5);
        Employee employee = new Employee("A", date, date, 100);
        store.add(employee);
        Report reportXML = new ReportXML(store);
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("\n<employee>")
                .append("\n    <fired>2020-01-05T00:00:00+03:00</fired>")
                .append("\n    <hired>2020-01-05T00:00:00+03:00</hired>")
                .append("\n    <name>A</name>")
                .append("\n    <salary>100.0</salary>")
                .append("\n</employee>")
                .append("\n");
        assertThat(reportXML.generate(filter -> true), is(expected.toString()));
    }
}