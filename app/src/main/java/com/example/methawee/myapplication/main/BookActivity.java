package com.example.methawee.myapplication.main;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.data.Book;
import com.example.methawee.myapplication.data.BookAdapter;
import com.example.methawee.myapplication.data.BookRepository;
import com.example.methawee.myapplication.data.RemoteBookRepository;

import java.util.ArrayList;

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

    public void setBookList(ArrayList<Book> books) {
        book_view = (ListView) findViewById(R.id.listview_books);
        ArrayList<Book> test = books;
        book_adapter = new BookAdapter(this, test);
        book_view.setAdapter(book_adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view_menu_item, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setQueryHint("search your book here ♡");
        return super.onCreateOptionsMenu(menu);
    }
}


