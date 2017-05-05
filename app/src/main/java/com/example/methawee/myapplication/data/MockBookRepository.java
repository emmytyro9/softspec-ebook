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
        books.add(new Book(1, "Introduction to Java", 13.95, 2015, "https://images-na.ssl-images-amazon.com/images/I/41xvbZoHAzL._SX397_BO1,204,203,200_.jpg"));
        books.add(new Book(10, "Introduction to C++", 19.95, 2016, "https://images-na.ssl-images-amazon.com/images/I/51iQ0T3KTTL._SX258_BO1,204,203,200_.jpg"));
        books.add(new Book(12, "Algorithms", 29.95, 2012, "https://upload.wikimedia.org/wikipedia/en/4/41/Clrs3.jpeg"));
        books.add(new Book(17, "Pascal Programming", 17.95, 2007, "https://images-na.ssl-images-amazon.com/images/I/51c6iRdHd0L._SX399_BO1,204,203,200_.jpg"));
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

    @Override
    public void sort(ArrayList<Book> books) {

    }

    @Override
    public ArrayList<Book> search(String newText) {
        return null;
    }

    @Override
    public Book getBookAt(int index) {
        return null;
    }
}
