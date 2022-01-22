package ru.job4j.isp.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Element {

    private String str;
    private List<Element> children = new ArrayList<>();

    public Element(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public List<Element> getChildren() {
        return children;
    }

    public void setChildren(List<Element> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Element element = (Element) o;
        return Objects.equals(str, element.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str);
    }

    @Override
    public String toString() {
        return "Element{" +
                "str='" + str + '\'' +
                '}';
    }
}
