package ru.job4j.isp.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Menu extends TreeElement implements Print {

    private final Element element;

    public Menu(String element, Action action) {
        this.element = new Element(element, action);
    }

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        Queue<Element> data = new LinkedList<>();
        data.add(element);
        while (!data.isEmpty()) {
            Element el = data.poll();
            stringBuilder.append(el).append(NEW_LINE);
            data.addAll(el.getChildren());
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean add(String parent, String child, Action action) {
        boolean rsl = false;
        Element element = findBy(parent);
        if (element != null) {
            element.getChildren().add(new Element(child, action));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Element findBy(String search) {
        Element rsl = null;
        Queue<Element> data = new LinkedList<>();
        data.add(element);
        while (!data.isEmpty()) {
            Element el = data.poll();
            if (search != null && search.equals(el.getStr())) {
                rsl = el;
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }

    public Action select(String itemName) {
        Element element = findBy(itemName);
        return element.getAction();
    }
}
