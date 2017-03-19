package ua.lviv.service;

import org.springframework.stereotype.Service;
import ua.lviv.entity.User;

/**
 * Created by Pavlo on 04/03/2017.
 */
public interface UserService {
    void add(String login, String password, String email, String phone);
    void edit(int id, String login, String password, String email, String phone);
    void delete(int id);
    User findById(int id);
    User findByLogin(String login);
}
