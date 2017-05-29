package com.example.methawee.myapplication.main.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.data.cart.User;

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


    }

}
