package ru.job4j.design.srp;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReportJSONTest  {

    @Test
    public void generate() throws JAXBException {
        MemStore store = new MemStore();
        Calendar date = new GregorianCalendar(2020, Calendar.JANUARY, 5);
        Employee employee = new Employee("A", date, date, 100);
        store.add(employee);
        Report reportJSON = new ReportJSON(store);
        StringBuilder expected = new StringBuilder()
                .append("[{")
                .append("\"name\":")
                .append("\"A\",")
                .append("\"hired\":")
                .append("{\"year\":2020,\"month\":0,\"dayOfMonth\":5,"
                        + "\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"fired\":")
                .append("{\"year\":2020,\"month\":0,\"dayOfMonth\":5,"
                        + "\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"salary\":")
                .append("100.0")
                .append("}]");
        assertThat(reportJSON.generate(filter -> true), is(expected.toString()));
    }
}