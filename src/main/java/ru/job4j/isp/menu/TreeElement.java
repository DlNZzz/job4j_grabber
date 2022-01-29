package ru.job4j.isp.menu;

import java.util.*;

public abstract class TreeElement {

    public boolean add(String parent, String child, Action action) {
        return false;
    }

    public Element findBy(String search) {
        return null;
    }

    public Action select(String itemName) {
        return null;
    }

    static class Element {

        private Action action;
        private String str;
        private final List<Element> children = new ArrayList<>();

        public Element(String str, Action action) {
            this.action = action;
            this.str = str;
        }

        public Action getAction() {
            return action;
        }

        public void setAction(Action action) {
            this.action = action;
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
            return "Element{"
                    + "str='" + str
                    + '\''
                    + '}';
        }
    }
}
