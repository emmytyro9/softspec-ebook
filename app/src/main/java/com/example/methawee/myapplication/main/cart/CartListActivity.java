package com.example.methawee.myapplication.main.cart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.methawee.myapplication.data.Book;
import com.example.methawee.myapplication.main.book.BookAdapter;

import java.util.ArrayList;

/**
 * Created by methawee on 5/21/2017 AD.
 */

public class CartListActivity extends AppCompatActivity {
    private BookAdapter book_adapter;
    public ArrayList<Book> books = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
