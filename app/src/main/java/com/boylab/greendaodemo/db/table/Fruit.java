package com.boylab.greendaodemo.db.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class Fruit {
    @Id(autoincrement = true)
    private Long id;

    @Index(unique = true)
    private String name;
    private float price;
    private float weigh;
    private String color;

    public Fruit(String name, float price, float weigh, String color) {
        this.name = name;
        this.price = price;
        this.weigh = weigh;
        this.color = color;
    }

    @Generated(hash = 1398843407)
    public Fruit(Long id, String name, float price, float weigh, String color) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weigh = weigh;
        this.color = color;
    }
    @Generated(hash = 2030614587)
    public Fruit() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return this.price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public float getWeigh() {
        return this.weigh;
    }
    public void setWeigh(float weigh) {
        this.weigh = weigh;
    }
    public String getColor() {
        return this.color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weigh=" + weigh +
                ", color='" + color + '\'' +
                '}';
    }
}
