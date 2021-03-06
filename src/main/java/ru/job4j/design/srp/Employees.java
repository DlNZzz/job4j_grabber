package ru.job4j.design.srp;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "employees")
public class Employees {

    private List<Employee> employees;

    public Employees() {

    }

    public Employees(List employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employees setEmployees(List<Employee> employees) {
        this.employees = employees;
        return this;
    }
}
