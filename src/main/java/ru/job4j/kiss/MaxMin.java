package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return math(value, comparator, c -> (c > 0));
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return math(value, comparator, c -> (c < 0));
    }

    public <T> T math(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T num = value.get(0);
        for (T val : value) {
            if (predicate.test(comparator.compare(val, num))) {
                num = val;
            }
        }
        return num;
    }
}