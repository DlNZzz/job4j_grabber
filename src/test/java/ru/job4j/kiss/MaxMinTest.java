package ru.job4j.kiss;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxMinTest {

    private List<Integer> list = new ArrayList<>();

    private Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1.equals(o2)) {
                return 0;
            }
            return o1 > o2 ? 1 : -1;
        }
    };

    @Test
    public void maxOne() {
        MaxMin maxMin = new MaxMin();
        list.add(1);
        list.add(53);
        list.add(32);
        int max = maxMin.max(list, comparator);
        assertThat(max, is(53));
    }

    @Test
    public void minOne() {
        MaxMin maxMin = new MaxMin();
        list.add(423423);
        list.add(1);
        list.add(32);
        int min = maxMin.min(list, comparator);
        assertThat(min, is(1));
    }
}

