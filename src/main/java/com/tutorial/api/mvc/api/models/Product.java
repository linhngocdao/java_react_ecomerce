package com.tutorial.api.mvc.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tblProduct")
public class Product {
    // this is a primary key
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) //auto generate id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    //validate = constraint
    @Column(nullable = false, unique = true, length = 300)
    private String name;
    private Double price;
    private String description;
    private Boolean status = true;

    // default constructor
    public Product() {
    }

    //calculated field = transient, not exist in database
    @Transient
    private Double discount;

    // Parameterized constructor
    public Product(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = true;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}