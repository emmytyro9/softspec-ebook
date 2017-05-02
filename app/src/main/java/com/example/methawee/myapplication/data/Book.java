package com.example.methawee.myapplication.data;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.net.URL;
import java.util.Comparator;

/**
 * Created by methawee on 4/20/2017 AD.
 */

public class Book implements Comparable, Serializable {
    private int id;
    private String title;
    private double price;
    private int year;
    private String imageUrl;


    public Book(int id, String title, double price, int year, String url) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.year = year;
        this.imageUrl = url;

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

    public int getYear() {
        return year;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String toString() {
        return id + ", " + title + " ($" + price + ")";
    }

    @Override
    public int compareTo(Object another) {
        Book book = (Book) another;
        char book_1 = book.title.charAt(0);
        char book_2 = this.title.charAt(0);
        if (book_2 < book_1)
            return 1;
        return -1;
    }
}

