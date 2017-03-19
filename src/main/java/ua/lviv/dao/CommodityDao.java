package ua.lviv.dao;

import ua.lviv.entity.Commodity;

import java.io.File;
import java.util.List;

/**
 * Created by Pavlo on 04/03/2017.
 */
public interface CommodityDao {

    void add(Commodity commodity);

    void edit(Commodity commodity);

    void delete(int id);

    Commodity findById(int id);

    List<Commodity> findByName(String name);

    List<Commodity> findByCategory(String category);

    List<Commodity> findAll();
}
