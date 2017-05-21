package com.example.methawee.myapplication.data.cart;

import com.example.methawee.myapplication.data.Book;

/**
 * Created by methawee on 5/21/2017 AD.
 */

public interface CartPresenter {

    public void addToCart(Book book);
    public void setChanged();
    public void removeBook(Book book);
}
