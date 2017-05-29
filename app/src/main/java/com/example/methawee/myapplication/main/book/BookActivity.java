package com.example.methawee.myapplication.main.book;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.data.Book;
import com.example.methawee.myapplication.data.BookRepository;
import com.example.methawee.myapplication.data.RemoteBookRepository;
import com.example.methawee.myapplication.data.cart.Cart;
import com.example.methawee.myapplication.data.cart.User;
import com.example.methawee.myapplication.main.book_detail.BookDetailActivity;
import com.example.methawee.myapplication.main.user.UserActivity;

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

    public void setupBookSelectedListener() {
        book_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BookActivity.this, BookDetailActivity.class);
                intent.putExtra("book", book_repository.getBookAt(position - 1));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view_menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final SearchView search_view = (SearchView) MenuItemCompat.getActionView(item);
        switch (item.getItemId()) {
            case R.id.action_search:
                search_view.setQueryHint("search your book ♥");
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
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void setBookList(ArrayList<Book> books) {
        book_view = (ListView) findViewById(R.id.listview_books);
        book_adapter = new BookAdapter(this, books);
        book_view.setAdapter(book_adapter);
    }

    @Override
    public void setConcurrency(int concurrency) {

    }

    public void user(View view) {
        Intent intent = new Intent(BookActivity.this, UserActivity.class);
        /* intent.putExtra("user", book_presenter.getUser()); */
        BookActivity.this.startActivity(intent);
    }

    public void show_cart(View view) {
        /* final Cart cart = book_presenter.getCart();
        final User user = book_presenter.getUser(); */
        final ListView cart_list = new ListView(this);

        new AlertDialog.Builder(this)
                .setTitle("Cart ♡")
                .setView(cart_list)
                .setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }


}


