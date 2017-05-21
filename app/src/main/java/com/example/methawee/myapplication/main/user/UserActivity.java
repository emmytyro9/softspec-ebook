package com.example.methawee.myapplication.main.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.data.cart.User;
import com.example.methawee.myapplication.main.BookAdapter;

/**
 * Created by methawee on 5/21/2017 AD.
 */

public class UserActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        user = (User) getIntent().getSerializableExtra("user");
        initial();

    }

    private void initial() {
//        name();
//        currency();
        cart();
    }

//    public void name() {
//        TextView name = (TextView) findViewById(R.id.name);
//        name.setText(user.name);
//    }
//
//    public void currency() {
//        TextView currency = (TextView) findViewById(R.id.currency);
//        currency.setText("currency: $" + user.currency);
//    }

    public void cart() {
        if (!user.customer_book.isEmpty()) {
            ListView listview = (ListView) findViewById(R.id.listview_books);
            listview.setAdapter(new BookAdapter(this, user.customer_book));
        }
    }
}
