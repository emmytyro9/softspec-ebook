package com.example.methawee.myapplication.data.cart;

import com.example.methawee.myapplication.data.Book;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by methawee on 5/21/2017 AD.
 */

public class Cart implements Serializable {

    private  ArrayList<Book> listbook = new ArrayList<Book>();
    private double balance ;
    public Cart(){
        balance = 0;
    }

    public ArrayList<Book> getListbook() {
        return listbook;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }
}