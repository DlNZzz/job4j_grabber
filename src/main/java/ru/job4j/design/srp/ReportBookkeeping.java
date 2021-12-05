package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportBookkeeping implements Report {

    private Store store;

    public ReportBookkeeping(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(NEW_LINE)
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() * 0.87).append(";")
                    .append(NEW_LINE);
        }
        return text.toString();
    }
}
