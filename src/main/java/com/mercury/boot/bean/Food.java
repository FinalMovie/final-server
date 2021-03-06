package com.mercury.boot.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FOOD")
public class Food {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
//    @SequenceGenerator(name = "SEQ", sequenceName = "MSI_USER_SEQ", allocationSize = 1)
    private long id;

    @Column
    private String name;
    @Column
    private double price;
    @Column
    private int calories;

    @Column
    private String image;

    public Food() {
    }

    public Food(long id, String name, int price, int calories, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.image = image;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
