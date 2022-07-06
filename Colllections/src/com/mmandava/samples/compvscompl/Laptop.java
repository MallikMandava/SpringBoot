package com.mmandava.samples.compvscompl;

public class Laptop implements Comparable<Laptop> {
    private int price;
    private String model;

    public Laptop(int price, String model, int ram) {
        this.price = price;
        this.model = model;
        this.ram = ram;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "price=" + price +
                ", model='" + model + '\'' +
                ", ram=" + ram +
                '}';
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    private int ram;


    @Override
    public int compareTo(Laptop laptop) {
        if ( this.getPrice() > laptop.getPrice())
        {
            return 1;
        }
        else {
            return -1;
        }
    }
}
