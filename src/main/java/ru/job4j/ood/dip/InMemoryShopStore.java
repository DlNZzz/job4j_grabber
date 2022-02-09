package ru.job4j.ood.dip;

import java.util.Set;

public class InMemoryShopStore implements ShopStore {

    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public boolean saveOrder(User user, Order order) {
        return false;
    }

    @Override
    public Set<Order> getOrders(User user) {
        return null;
    }

    @Override
    public Set<User> getUsers() {
        return null;
    }
}
