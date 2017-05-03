package com.example.methawee.myapplication.main;


import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;


import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.data.Book;
import com.example.methawee.myapplication.data.BookRepository;
import com.example.methawee.myapplication.data.RemoteBookRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BookActivity extends AppCompatActivity implements BookView {

    private ListView book_view;
    private BookPresenter book_presenter;
    private BookRepository book_repository;
    private BookAdapter book_adapter;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        book_repository = RemoteBookRepository.getInstance();
        book_presenter = new BookPresenter(this, book_repository);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view_menu_item, menu);
        MenuItem search_item = menu.findItem(R.id.action_search);
        final SearchView search_view = (SearchView) MenuItemCompat.getActionView(search_item);
        search_view.setQueryHint("search your book here ♡");
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                search_view.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               book_presenter.search(newText);
                return true;
            }

        });

        return super.onCreateOptionsMenu(menu);
    }

    public void setBookList(ArrayList<Book> books) {
        book_view = (ListView) findViewById(R.id.listview_books);
        book_adapter = new BookAdapter(this, books);
        book_view.setAdapter(book_adapter);
    }
}


