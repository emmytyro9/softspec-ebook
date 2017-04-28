package com.example.methawee.myapplication.data;

import android.os.AsyncTask;

import com.example.methawee.myapplication.data.decoders.BookJSONDecoder;
import com.example.methawee.myapplication.utils.UrlFetcher;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by methawee on 4/28/2017 AD.
 */

public class RemoteBookRepository extends BookRepository {

    private List<Book> books;

    private static RemoteBookRepository instance = null;

    private RemoteBookRepository() {
        books = new ArrayList<Book>();
    }

    public static RemoteBookRepository getInstance() {
        if (instance == null)
            instance = new RemoteBookRepository();
        return instance;
    }

    @Override
    public void fetchAllBooks() {
        BookFetcherTask task = new BookFetcherTask();
        task.execute();
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    public class BookFetcherTask extends AsyncTask<Void,Void,ArrayList<Book>> {
        @Override
        protected ArrayList<Book> doInBackground(Void... params) {
            String bookListJsonStr = loadJSON();
            if (bookListJsonStr != null) {
                return BookJSONDecoder.createListFromJSONStr(bookListJsonStr);
            } else {
                return null;
            }
        }

        private String loadJSON() {
            URL booksURL = null;
            try {
                booksURL = new URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return (new UrlFetcher(booksURL)).fetch();
        }

        @Override
        protected void onPostExecute(ArrayList<Book> results) {
            if (results != null) {
                books.clear();
                books.addAll(results);
                setChanged();
                notifyObservers();
            }
        }
    }

}
