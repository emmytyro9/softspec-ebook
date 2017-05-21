package com.example.methawee.myapplication.data.cart;

import com.example.methawee.myapplication.data.Book;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by methawee on 5/21/2017 AD.
 */

public class Cart implements Serializable {

    private ArrayList<Book> cart = new ArrayList<>();
    private User user;
    private CartPresenter cart_presenter;

    public Cart(User user, CartPresenter cart_presenter) {
        this.user = user;
        this.cart_presenter = cart_presenter;
    }

    public void add(Book book) {
        cart.add(book);
    }

    public void remove(Book book) {


    }

    public void clear() {
        cart.clear();
    }

    public ArrayList<Book> getBook() {
        return cart;
    }

    public int sum() {
        int sum = 0;
        for (int i = 0; i < cart.size(); i++) {
            sum += cart.get(i).getPrice();
        }
        return sum;
    }
}
