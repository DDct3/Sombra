package ua.lviv.service.implementation;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.dao.BasketDao;
import ua.lviv.entity.Basket;
import ua.lviv.entity.User;
import ua.lviv.service.BasketService;

import java.util.Date;
import java.util.List;

/**
 * Created by Pavlo on 05/03/2017.
 */
@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketDao basketDao;

    public void add(String name, double price, User user) {
        basketDao.add(name, price, user);
    }

    public void delete(int id) {
        basketDao.delete(id);
    }

    public Basket findByName(String name) {
        return basketDao.findByName(name);
    }

    public Basket findById(int id) {
        return basketDao.findById(id);
    }

    public List<Basket> showAll(int id) {
        return basketDao.showAll(id);
    }
}
