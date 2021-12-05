package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportHTML implements Report {

    private Store store;

    public ReportHTML(Store store) {
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
}
