package com.example.entity;

public class Product {
    private int id;
    private String name;
    private Double price;


    public Product(int id, String name, Double price) {
        setId(id);
        setName(name);
        setPrice(price);
    }

    public Product() {
    }

    public Product(String name, Double price) {
        setName(name);
        setPrice(price);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id<=0) throw new RuntimeException("Id must be more than 1");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 100) throw new RuntimeException("Name can not be more than 100 characters.");
        this.name=name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if(price<=0) throw new RuntimeException("Price must be more than zero!");
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }


}
