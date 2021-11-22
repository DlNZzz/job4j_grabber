package ru.job4j.kiss;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxMinTest {

    private final List<Integer> list = List.of(1, 53, 32);
    private final MaxMin maxMin = new MaxMin();

    private Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 > o2 ? 1 : -1;
        }
    };

    @Test
    public void max() {
        int max = maxMin.max(list, comparator);
        assertThat(max, is(53));
    }

    @Test
    public void min() {
        int min = maxMin.min(list, comparator);
        assertThat(min, is(1));
    }

    @Test
    public void math() {
        int min = maxMin.math(list, comparator, c -> (c < 0));
        assertThat(min, is(1));
    }
}

