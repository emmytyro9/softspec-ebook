package com.example.methawee.myapplication.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


/**
 * Created by methawee on 4/20/2017 AD.
 */

public abstract class BookRepository extends Observable {

    public abstract void fetchAllBooks();

    public abstract List<Book> getAllBooks();

    public abstract void sort(ArrayList<Book> books);

    public abstract ArrayList<Book> search(String newText);

    public abstract Book getBookAt(int index);
}
