package com.example.methawee.myapplication.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.data.Book;
import com.example.methawee.myapplication.data.BookRepository;
import com.example.methawee.myapplication.data.RemoteBookRepository;

import java.util.List;

public class BookActivity extends AppCompatActivity implements BookView {

    private ListView bookView;
    private BookPresenter bookPresenter;
    private BookRepository bookRepository;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        bookRepository = RemoteBookRepository.getInstance();
        bookPresenter = new BookPresenter(bookRepository, this);
    }


    public void setBookList(List books) {

        bookView = (ListView) findViewById(R.id.listview_books);
        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, books);
        bookView.setAdapter(adapter);
    }



}


