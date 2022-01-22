package ru.job4j.isp.menu;

import java.util.LinkedList;
import java.util.Queue;

public interface Tree {

    public boolean add(Element parent, Element child);

    public Element findBy(Element search);
}
