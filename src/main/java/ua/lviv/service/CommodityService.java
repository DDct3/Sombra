package ua.lviv.service;

import com.mysql.jdbc.Blob;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ua.lviv.entity.Commodity;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pavlo on 04/03/2017.
 */
public interface CommodityService {

    void add(String name, double price, String category, String description, byte[] file) throws IOException;

    void edit(int id, String name, double price, String category, String description, byte[] file) throws IOException;

    void delete(int id);

    Commodity findById(int id);

    List<Commodity> findByName(String name);

    List<Commodity> findByCategory(String category);

    List<Commodity> findAll();
}
