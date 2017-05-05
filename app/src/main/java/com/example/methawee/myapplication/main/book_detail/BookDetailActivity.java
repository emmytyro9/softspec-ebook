package com.example.methawee.myapplication.main.book_detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.data.Book;
import com.example.methawee.myapplication.data.BookRepository;
import com.example.methawee.myapplication.data.RemoteBookRepository;
import com.example.methawee.myapplication.main.BookPresenter;
import com.squareup.picasso.Picasso;

/**
 * Created by methawee on 5/3/2017 AD.
 */

public class BookDetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView price;
    private TextView year;
    private ImageView img;
    private Book book;
    private BookRepository book_repository;
    private BookPresenter book_presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        Intent data = getIntent();
        book_repository = (BookRepository) data.getSerializableExtra("book");
        getData();
        loadBook(book);
    }

    private void getData() {
        img = (ImageView) findViewById(R.id.img);
        title = (TextView) findViewById(R.id.title);
        price = (TextView) findViewById(R.id.price);
        year = (TextView) findViewById(R.id.year);
    }

    private void loadBook(Book book) {
        this.setTitle(book.getTitle());
        Picasso.with(this).load(Uri.parse(book.getImageUrl())).error(R.drawable.ic_nocover).into(img);
        title.setText(book.getTitle());
        price.setText(String.valueOf(book.getPrice()));
        year.setText(String.valueOf(book.getYear()));
    }
}
