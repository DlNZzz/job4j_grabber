package ru.job4j.ood.lsp;

public class A {
    public boolean aVoid(boolean b) {
        if (!b) {
            System.out.println(b);
        }
        b = true;
        if (b) {
            b = false;
        }
        return b;
    }
}
