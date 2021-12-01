package ru.job4j.ood.srp;

public class TaskResource implements Task {

    @Override
    public int task(int num) {
        return new QuadraticEquation().solution(num);
    }

    @Override
    public int taskTwo(int a, int b) {
        return 0;
    }
}
