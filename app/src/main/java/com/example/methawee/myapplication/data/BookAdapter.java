package com.example.methawee.myapplication.data;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.main.BookView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by methawee on 4/30/2017 AD.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    private static class ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView price;
    }

    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Book book = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_book, parent, false);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(book.getTitle());
        viewHolder.price.setText("$" + (Double.toString(book.getPrice())));
        Picasso.with(getContext()).load(Uri.parse(book.getImageUrl())).error(R.drawable.ic_nocover).into(viewHolder.img);
        return convertView;
    }
}
