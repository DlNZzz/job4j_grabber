package ru.job4j.design.srp;

import javax.xml.bind.JAXBException;
import java.util.function.Predicate;

public interface Report {
    String NEW_LINE = System.lineSeparator();

    String generate(Predicate<Employee> filter) throws JAXBException;
}