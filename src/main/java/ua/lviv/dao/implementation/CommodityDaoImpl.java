package ua.lviv.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.dao.CommodityDao;
import ua.lviv.entity.Commodity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.File;
import java.util.List;

/**
 * Created by Pavlo on 04/03/2017.
 */
@Repository
public class CommodityDaoImpl implements CommodityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void add(Commodity commodity) {
        entityManager.persist(commodity);
    }

    @Transactional
    public void edit(Commodity commodity) {
        entityManager.merge(commodity);
    }

    @Transactional
    public void delete(int id) {
        Commodity commodity =entityManager.find(Commodity.class, id);
        entityManager.remove(commodity);
    }

    @Transactional
    public Commodity findById(int id)
    {
        return entityManager.find(Commodity.class, id);
    }

    public List<Commodity> findByName(String name) {
        Query query = entityManager.createQuery("select c from Commodity c where c.name = :name");
        query.setParameter("name", name);
        List<Commodity> commodities = query.getResultList();
        return commodities;
    }

    public List<Commodity> findByCategory(String category) {
        Query query = entityManager.createQuery("select c from Commodity c where c.category = :category");
        query.setParameter("category", category);
        List<Commodity> commodities = query.getResultList();
        return commodities;
    }

    public List<Commodity> findAll() {
        return entityManager.createQuery("select c from Commodity c").getResultList();
    }
}
