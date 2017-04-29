package com.example.methawee.myapplication.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by methawee on 4/30/2017 AD.
 */

public class MockBookRepository extends BookRepository {

    private List<Book> books;
    private static MockBookRepository instance = null;

    public static MockBookRepository getInstance() {
        if (instance == null) {
            instance = new MockBookRepository();
        }
        return instance;
    }

    private MockBookRepository() {
        books = new ArrayList<Book>();
        books.add(new Book(1, "Introduction to Java", 13.95, 2015, ""));
        books.add(new Book(10, "Introduction to C++", 19.95, 2016, ""));
        books.add(new Book(12, "Algorithms", 29.95, 2012, ""));
        books.add(new Book(17, "Pascal Programming", 17.95, 2007, ""));
    }

    @Override
    public void fetchAllBooks() {
        setChanged();
        notifyObservers();
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }
}
