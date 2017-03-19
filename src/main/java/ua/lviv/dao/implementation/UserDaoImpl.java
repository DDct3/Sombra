package ua.lviv.dao.implementation;

import org.springframework.stereotype.Repository;
import ua.lviv.dao.UserDao;
import ua.lviv.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Pavlo on 04/03/2017.
 */
@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Transactional
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Transactional
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    public User findByLogin(String login) {
        return (User) entityManager.createQuery("select u from User u where u.login=:login").setParameter("login", login).getSingleResult();
    }
}
