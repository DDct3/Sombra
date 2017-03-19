package ua.lviv.entity;

import com.mysql.fabric.xmlrpc.base.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pavlo on 05/03/2017.
 */
@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;
    @Column(nullable = true)
    private Date date;
    @Column
    private double price;
    @ManyToOne
    private User user;


    public Basket(String name, Date date, double price, User user) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.user = user;
    }

    public Basket(String name, Date date, double price) {
        this.name = name;
        this.date = date;
        this.price = price;
    }

    public Basket() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
