package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportProgrammer implements Report {

    private Store store;

    public ReportProgrammer(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<tr>Name; Hired; Fired; Salary;</tr>");
        for (Employee employee : store.findBy(filter)) {
            text.append(NEW_LINE + "<tr>")
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</tr>" + NEW_LINE);
        }
        return text.toString();
    }
}
