package ua.lviv.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.lviv.dao.UserDao;
import ua.lviv.entity.User;
import ua.lviv.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavlo on 04/03/2017.
 */
@Service(value = "userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    public void add(String login, String password, String email, String phone) {

        userDao.add(new User(login, password, email, phone));

    }

    public void edit(int id, String login, String password, String email, String phone) {
        User user = userDao.findById(id);
        if (login != null && !login.equalsIgnoreCase("")){
            user.setLogin(login);
        }
        if (password != null && !password.equalsIgnoreCase("")){
            user.setPassword(password);
        }
        if (email != null && !email.equalsIgnoreCase("")){
            user.setEmail(email);
        }
        if (phone != null && !phone.equalsIgnoreCase("")){
            user.setPhone(phone);
        }

        userDao.edit(user);

    }

    public void delete(int id) {
        userDao.delete(userDao.findById(id));
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userDao.findByLogin(login);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), simpleGrantedAuthorities);
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
