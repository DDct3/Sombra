package ua.lviv.dao;

import ua.lviv.entity.User;

import java.util.List;

/**
 * Created by Pavlo on 04/03/2017.
 */
public interface UserDao {
    void add(User user);
    void edit(User user);
    void delete(User user);
    User findById(int id);
    User findByLogin(String login);
}
