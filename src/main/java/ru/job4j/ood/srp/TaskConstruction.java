package ru.job4j.ood.srp;

public class TaskConstruction implements Task {

    private static TaskConstruction libraryMath = null;

    private TaskConstruction() {

    }

    public TaskConstruction getFactorial() {
        if (libraryMath == null) {
            libraryMath = new TaskConstruction();
        }
        return libraryMath;
    }

    public int task(int num) {
        return 0;
    }

    public int taskTwo(int a, int b) {
        return 0;
    }
}
