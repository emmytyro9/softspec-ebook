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

                ArrayList<Book> found_book = new ArrayList<Book>();
                ArrayList<Book> books = new ArrayList<Book>(book_repository.getAllBooks());
                book_view = (ListView) findViewById(R.id.listview_books);

                if (newText != null && !newText.isEmpty()) {
                    for (Book book : books) {
                        if (book.getTitle().contains(newText)) {
                            found_book.add(book);
                        } /* if (Integer.valueOf(newText) == book.getYear()) {
                            found_book.add(book);
                        } */
                    }
                    sort(found_book);
                    book_adapter = new BookAdapter(BookActivity.this, found_book);
                    book_view.setAdapter(book_adapter);
                } else {
                    ArrayList<Book> book = books;
                    sort(book);
                    book_adapter = new BookAdapter(BookActivity.this, book);
                    book_view.setAdapter(book_adapter);
                }

                return true;
            }

        });

        return super.onCreateOptionsMenu(menu);
    }

    public void setBookList(ArrayList<Book> books) {
        book_view = (ListView) findViewById(R.id.listview_books);
        ArrayList<Book> book = books;
        book_adapter = new BookAdapter(this, book);
        book_view.setAdapter(book_adapter);
    }

    public void sort(ArrayList<Book> books) {
        Collections.sort(books, new Comparator<Book>() {
                    @Override
                    public int compare(final Book book_1, final Book book_2) {
                        return book_1.getTitle().compareTo(book_2.getTitle());
                    }
                }
        );
    }
}


