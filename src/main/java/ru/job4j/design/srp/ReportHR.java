package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportHR implements Report {

    private Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String lineS = System.lineSeparator();
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(NEW_LINE);
        List<Employee> employees = store.findBy(filter).stream().sorted(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o2.getSalary() > o1.getSalary() ? 1 : -1;
            }
        }).collect(Collectors.toList());
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(lineS);
        }
        return text.toString();
    }
}
