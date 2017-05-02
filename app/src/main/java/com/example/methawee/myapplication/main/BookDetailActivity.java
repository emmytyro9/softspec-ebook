package com.example.methawee.myapplication.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.data.Book;
import com.squareup.picasso.Picasso;

/**
 * Created by methawee on 5/3/2017 AD.
 */

public class BookDetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView price;
    private TextView year;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        img = (ImageView) findViewById(R.id.img);
        title = (TextView) findViewById(R.id.title);
        price = (TextView) findViewById(R.id.price);
        year = (TextView) findViewById(R.id.year);

        Book book = (Book) getIntent().getSerializableExtra("book");

    }

    private void loadBook(Book book) {
        this.setTitle(book.getTitle());
        Picasso.with(this).load(Uri.parse(book.getImageUrl())).error(R.drawable.ic_nocover).into(img);
        title.setText(book.getTitle());
        price.setText(String.valueOf(book.getPrice()));
        year.setText(String.valueOf(book.getYear()));
    }

}
