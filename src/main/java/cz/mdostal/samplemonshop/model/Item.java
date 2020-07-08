package cz.mdostal.samplemonshop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Item implements Serializable {

    private static final long serialVersionUID = 666L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private Double price;

    public Item() {
    }

    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}