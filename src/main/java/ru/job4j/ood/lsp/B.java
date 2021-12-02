package ru.job4j.ood.lsp;

public class B extends A {
    @Override
    public boolean aVoid(boolean b) {
        if (b) {
            System.out.println(b);
        }
        b = true;
        return b;
    }
}
