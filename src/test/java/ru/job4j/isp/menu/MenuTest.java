package ru.job4j.isp.menu;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static ru.job4j.isp.menu.Print.NEW_LINE;

public class MenuTest {

    private final Menu tree = new Menu("1", new Action());

    @Test
    public void testFindBy() {
        tree.add("1", "2", new Action());
        tree.add("1", "3", new Action());
        tree.add("1", "4", new Action());
        tree.add("4", "5", new Action());
        tree.add("5", "6", new Action());
        assertNotNull(tree.findBy("5"));
    }

    @Test
    public void testAddTrue() {
        assertTrue(
                tree.add("1", "2", new Action())
        );
    }

    @Test
    public void testAddTrueTwo() {
        tree.add("1", "2", new Action());
        assertTrue(tree.add("2", "3", new Action()));
    }

    @Test
    public void testAddFalse() {
        assertFalse(tree.add("2", "3", new Action()));
    }

    @Test
    public void testPrint() {
        tree.add("1", "2", new Action());
        tree.add("1", "3", new Action());
        tree.add("1", "4", new Action());
        tree.add("4", "5", new Action());
        tree.add("5", "6", new Action());
        assertThat(
                tree.print(),
                is("Element{str='1'}" + NEW_LINE
                        + "Element{str='2'}" + NEW_LINE
                        + "Element{str='3'}" + NEW_LINE
                        + "Element{str='4'}" + NEW_LINE
                        + "Element{str='5'}" + NEW_LINE
                        + "Element{str='6'}" + NEW_LINE)
        );
    }
}