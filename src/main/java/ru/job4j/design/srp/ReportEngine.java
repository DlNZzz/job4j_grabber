package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<tr>Name; Salary;</tr>");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator() + "<tr>")
                    .append(employee.getName())
                    .append(employee.getSalary() * 0.87)
                    .append("</tr>" + System.lineSeparator());
        }
        return text.toString();
    }

    public String generateJSON(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        Gson lib = new GsonBuilder().create();
        return lib.toJson(list);
    }

    public String generateXML(Predicate<Employee> filter) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(store.findBy(filter), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
