package com.example.repository;

import com.example.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private static int lastId;
    private static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(++lastId, "rice", 500.00));
        products.add(new Product(++lastId, "pasta", 200.0));
    }

    public static void addProduct(String name, double price) {
        if (!doesProductExist(name)) {
            Product product = new Product(++lastId, name.toLowerCase(), price);
            products.add(product);
        } else throw new RuntimeException("");
    }

    public static void deleteProductByName(String name) {
        Product product = getProductByName(name)
                .orElseThrow(() -> new RuntimeException("Product with name '" + name + "' not found"));
        products.remove(product);
    }

    public static void deleteProductById(int id) {
        Product product = getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID '" + id + "' not found"));
        products.remove(product);
    }
    public static void updateProductByName(String name, double price){
        if (doesProductExist(name)){
            deleteProductByName(name);
            addProduct(name,price);
        }
        else throw new RuntimeException("Product with Name '" + name + "' not found");
    }

    public static List<Product> getAllProducts() {
        return products;
    }


    public static Optional<Product> getProductByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public static Optional<Product> getProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    private static boolean doesProductExist(String name) {
        return products.stream().anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }

    private static boolean doesProductExist(int id) {
        return products.stream().anyMatch(product -> product.getId() == id);
    }
}
