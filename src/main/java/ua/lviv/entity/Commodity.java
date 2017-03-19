package ua.lviv.entity;

import com.mysql.jdbc.Blob;

import javax.persistence.*;

/**
 * Created by Pavlo on 04/03/2017.
 */
@Entity
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String category;
    private String description;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    public Commodity(String name, double price, String category, String description, Blob blob) {
    }

    public Commodity(String name, double price, String category, String description, byte[] image) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
    }

    public Commodity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
