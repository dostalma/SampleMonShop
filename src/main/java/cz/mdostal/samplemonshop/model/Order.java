package cz.mdostal.samplemonshop.model;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "ORDER_HISTORY")
public class Order implements Serializable {

    private static final long serialVersionUID = 1234567L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private long id;

    @Column(name = "DATE")
    Date date;

    @OneToMany(orphanRemoval = true,
            cascade=CascadeType.ALL)
    @IndexColumn(name="INDEX_ITEM")
    List<Item> items = new LinkedList<Item>();

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "date=" + date +
                ", items=" + items +
                '}';
    }
}
