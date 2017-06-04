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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.data.Book;
import com.example.methawee.myapplication.data.BookRepository;
import com.example.methawee.myapplication.data.RemoteBookRepository;
import com.example.methawee.myapplication.data.cart.Cart;
import com.example.methawee.myapplication.data.cart.User;
import com.example.methawee.myapplication.main.book_detail.BookDetailActivity;
import com.example.methawee.myapplication.main.cart.CartActivity;
import com.example.methawee.myapplication.main.user.UserActivity;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity implements BookView {


    public static ArrayList<Book> cartArrayList = new ArrayList<Book>();

    private ListView book_view;
    private BookPresenter book_presenter;
    private BookRepository book_repository;
    private BookAdapter book_adapter;
    ArrayAdapter<Book> bookAdapter;
    public static Cart user = new Cart();

    Button cart ;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);


        book_view = (ListView) findViewById(R.id.listview_books) ;
        book_repository = RemoteBookRepository.getInstance();
        bookAdapter =  createAdapter(new ArrayList<Book>());
        book_presenter = new BookPresenter(this, book_repository);
        book_view.setAdapter(bookAdapter);
        cart = (Button) findViewById(R.id.button_cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(i);
            }
        });
    }

    private ArrayAdapter<Book> createAdapter(ArrayList<Book> books){

        return new ArrayAdapter<Book>(this,android.R.layout.simple_list_item_1,books);
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
        bookAdapter = createAdapter(books);
        book_view.setAdapter(bookAdapter);
        book_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BookActivity.this);
                alertDialog.setTitle("Confirmation");
                alertDialog.setMessage("Add to cart?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Book is added", Toast.LENGTH_SHORT).show();
                        cartArrayList.add((Book) book_view.getItemAtPosition(position));

                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.show();
            }

        });
    }
}


