package com.example.methawee.myapplication.main.book;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.data.Book;
import com.example.methawee.myapplication.data.BookRepository;
import com.example.methawee.myapplication.data.RemoteBookRepository;
import com.example.methawee.myapplication.data.cart.Cart;
import com.example.methawee.myapplication.main.cart.CartActivity;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity implements BookView {


    public static ArrayList<Book> cartArrayList = new ArrayList<Book>();

    private ListView book_view;
    private BookPresenter book_presenter;
    private BookRepository book_repository;
    ArrayAdapter<Book> book_adapter;
    public static Cart user = new Cart();
    Button cart, sort_title, sort_year;
    public static int type;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        book_repository = RemoteBookRepository.getInstance();
        book_presenter = new BookPresenter(this, book_repository);
        cart = (Button) findViewById(R.id.button_cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(i);
            }
        });
        sort_title = (Button) findViewById(R.id.sort_title);
        sort_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;
                book_presenter.sort(type);
                book_presenter.update(book_repository, book_view);
            }
        });
        sort_year = (Button) findViewById(R.id.sort_year);
        sort_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 2;
                book_presenter.sort(type);
                book_presenter.update(book_repository, book_view);
            }
        });
    }

    private ArrayAdapter<Book> create_adapter(ArrayList<Book> books) {

        return new BookAdapter(this, books);
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
        book_adapter = create_adapter(books);
        book_view.setAdapter(book_adapter);
        book_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BookActivity.this);
                alertDialog.setTitle("Confirmation");
                alertDialog.setMessage("Add to cart ?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Book is added", Toast.LENGTH_SHORT).show();
                        cartArrayList.add((Book) book_view.getItemAtPosition(position));

                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
            }
        });
    }
}