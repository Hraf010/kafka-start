package com.hraf.kafkaspringintro.entities;

public class PizzaOrder {
    private String name ;
    private double price;

    public PizzaOrder() {
    }

    public PizzaOrder(String name, double price) {
        this.name = name;
        this.price = price;
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
}
