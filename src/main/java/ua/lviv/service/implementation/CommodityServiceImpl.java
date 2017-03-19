package ua.lviv.service.implementation;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ua.lviv.dao.CommodityDao;
import ua.lviv.entity.Commodity;
import ua.lviv.service.CommodityService;

import java.io.*;
import java.util.List;

/**
 * Created by Pavlo on 04/03/2017.
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityDao commodityDao;

    public void add(String name, double price, String category, String description, byte[] image) {
        commodityDao.add(new Commodity(name, price, category, description, image));
    }

    public void edit(int id, String name, double price, String category, String description, byte[] image) throws IOException {

        Commodity commodity = commodityDao.findById(id);

        if (name != null && !name.equalsIgnoreCase("")) {
            commodity.setName(name);
        }
        if (price != 0) {
            commodity.setPrice(price);
        }
        if (category != null && !category.equalsIgnoreCase("")) {
            commodity.setCategory(category);
        }
        if (description.length() < 0 && description != null && !description.equalsIgnoreCase("")) {
            commodity.setDescription(description);
        }
        if (image.length != 0){
            commodity.setImage(image);
        }
        commodityDao.edit(commodity);
    }

    public void delete(int id) {
        commodityDao.delete(id);
    }

    public Commodity findById(int id)
    {
       return commodityDao.findById(id);
    }

    public List<Commodity> findByName(String name) {
        return commodityDao.findByName(name);
    }

    public List<Commodity> findByCategory(String category){
        return commodityDao.findByCategory(category);
    }

    public List<Commodity> findAll() {
        return commodityDao.findAll();
    }
}
