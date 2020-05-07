package com.mercury.boot.bean;


import javax.persistence.*;

@Entity
@Table(name="MOVIES")
public class Movies {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
//    @SequenceGenerator(name = "SEQ", sequenceName = "MSI_USER_SEQ", allocationSize = 1)
    private long id;

    @Column
    private String name;
    @Column
    private int price;
    @Column
    private String description;
    @Column
    private String image;

    public Movies() {
    }

    public Movies(long id, String name,int price,String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
