package ru.job4j.isp.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Menu implements Tree, Print {

    private Element element;

    public Menu(Element element) {
        this.element = element;
    }

    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        Queue<Element> data = new LinkedList<>();
        data.add(element);
        while (!data.isEmpty()) {
            Element el = data.poll();
            stringBuilder.append(el + "\n");
            data.addAll(el.getChildren());
        }
        return stringBuilder.toString();
    }

    public boolean add(Element parent, Element child) {
        boolean rsl = false;
        if (findBy(parent) != null) {
            parent.getChildren().add(child);
            rsl = true;
        }
        return rsl;
    }

    public Element findBy(Element search) {
        Element rsl = null;
        Queue<Element> data = new LinkedList<>();
        data.add(element);
        while (!data.isEmpty()) {
            Element el = data.poll();
            if (search.equals(el)) {
                rsl = el;
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }

    public void selection() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Element element = findBy(new Element(reader.readLine()));
    }
}
