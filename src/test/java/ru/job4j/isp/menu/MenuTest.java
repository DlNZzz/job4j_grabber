package ru.job4j.isp.menu;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MenuTest {

    @Test
    public void testFindBy() {
        Element element1 = new Element("1");
        Element element4 = new Element("4");
        Element element5 = new Element("5");
        Menu tree = new Menu(element1);
        tree.add(element1, new Element("2"));
        tree.add(element1, new Element("3"));
        tree.add(element1, element4);
        tree.add(element4, element5);
        tree.add(element5, new Element("6"));
        assertThat(
                tree.findBy(element5),
                is(new Element("5"))
        );
    }

    @Test
    public void testAddTrue() {
        Menu tree = new Menu(new Element("1"));
        assertTrue(
                tree.add(new Element("1"), new Element("2"))
        );
    }

    @Test
    public void testAddTrueTwo() {
        Element element1 = new Element("1");
        Element element2 = new Element("2");
        Menu tree = new Menu(element1);
        tree.add(element1, element2);
        assertTrue(
                tree.add(element2, new Element("3"))
        );
    }

    @Test
    public void testAddFalse() {
        Menu tree = new Menu(new Element("1"));
        assertFalse(
                tree.add(new Element("2"), new Element("3"))
        );
    }

    @Test
    public void testPrint() {
        Element element1 = new Element("1");
        Element element4 = new Element("4");
        Element element5 = new Element("5");
        Menu tree = new Menu(element1);
        tree.add(element1, new Element("2"));
        tree.add(element1, new Element("3"));
        tree.add(element1, element4);
        tree.add(element4, element5);
        tree.add(element5, new Element("6"));
        assertThat(
                tree.print(),
                is("Element{str='1'}\n"
                        + "Element{str='2'}\n"
                        + "Element{str='3'}\n"
                        + "Element{str='4'}\n"
                        + "Element{str='5'}\n"
                        + "Element{str='6'}\n")
        );
    }
}