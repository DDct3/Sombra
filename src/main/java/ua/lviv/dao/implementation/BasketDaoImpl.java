package ua.lviv.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.dao.BasketDao;
import ua.lviv.entity.Basket;
import ua.lviv.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Pavlo on 05/03/2017.
 */
@Repository
@Transactional
public class BasketDaoImpl implements BasketDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void add(String name, double price, User user) {
        entityManager.persist(new Basket(name,new Date(), price, user));
    }
    @Transactional
    public Basket findById(int id) {
        return entityManager.find(Basket.class, id);
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.find(Basket.class, id));
    }

    public Basket findByName(String name) {
        return entityManager.find(Basket.class, name);
    }

    @Transactional
    public List<Basket> showAll(int id) {
        Query query = entityManager.createQuery("select b from Basket b where user.id=:id");
        query.setParameter("id", id);
        List<Basket> basketList = query.getResultList();
        return basketList;

    }
}
