package ua.lviv.dao;

import ua.lviv.entity.Basket;
import ua.lviv.entity.User;

import java.util.List;

/**
 * Created by Pavlo on 05/03/2017.
 */
public interface BasketDao {
    void add(String name, double price, User user);
    void delete(int id);
    Basket findByName(String name);
    Basket findById(int id);
    List<Basket> showAll(int id);
}
