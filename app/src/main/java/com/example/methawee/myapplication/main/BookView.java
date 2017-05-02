package com.example.methawee.myapplication.main;

import com.example.methawee.myapplication.data.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by methawee on 4/20/2017 AD.
 */

public interface BookView {

    void setBookList(ArrayList<Book> books);
    void sort(ArrayList<Book> books);
}
