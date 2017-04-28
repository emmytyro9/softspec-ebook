package com.example.methawee.myapplication.data;

import android.support.annotation.NonNull;

/**
 * Created by methawee on 4/20/2017 AD.
 */

public class Book {

    private int id;
    private String title;
    private double price;
    private int year;
    private String imageUrl;


    public Book(int id, String title, double price, int year) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.year = year;
        this.imageUrl = null;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getPublicationYear() {
        return year;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String toString() {
        return title + " (" + price + ")";
    }

}
