package com.example.methawee.myapplication.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.data.Book;
import com.example.methawee.myapplication.data.BookAdapter;
import com.example.methawee.myapplication.data.BookRepository;
import com.example.methawee.myapplication.data.MockBookRepository;
import com.example.methawee.myapplication.data.RemoteBookRepository;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity implements BookView {

    private ListView bookView;
    private BookPresenter bookPresenter;
    private BookRepository bookRepository;
    private BookAdapter bookAdapter;


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        bookRepository = RemoteBookRepository.getInstance();
        bookPresenter = new BookPresenter(this, bookRepository);


    }


    public void setBookList(ArrayList<Book> books) {
        bookView = (ListView) findViewById(R.id.listview_books);
        ArrayList<Book> test = books;
        bookAdapter = new BookAdapter(this, test);
        bookView.setAdapter(bookAdapter);
    }

}


