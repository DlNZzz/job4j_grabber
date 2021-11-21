package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        T max = value.get(0);
        for (T val : value) {
            max = comparator.compare(max, val) > 0 ? max : val;
        }
        return max;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        T min = value.get(0);
        for (T val : value) {
            min = comparator.compare(min, val) < 0 ? min : val;
        }
        return min;
    }
}