package com.example.methawee.myapplication.data.cart;

import com.example.methawee.myapplication.data.Book;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by methawee on 5/21/2017 AD.
 */

public class User implements Serializable {

    public String name;
    public ArrayList<Book> customer_book = new ArrayList<>();
    public int currency;

    public User(String name) {
        this.name = name;
        currency = 0;
    }

    public void addBooks(ArrayList<Book> books) {
        for (int i = 0; i < customer_book.size(); i++) {
            customer_book.add(books.get(i));
        }
    }
}
