package com.example.methawee.myapplication.data;

import android.os.AsyncTask;
import android.widget.ListView;

import com.example.methawee.myapplication.R;
import com.example.methawee.myapplication.data.decoders.BookJSONDecoder;
import com.example.methawee.myapplication.main.BookActivity;
import com.example.methawee.myapplication.main.BookAdapter;
import com.example.methawee.myapplication.main.BookView;
import com.example.methawee.myapplication.utils.UrlFetcher;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by methawee on 4/28/2017 AD.
 */

public class RemoteBookRepository extends BookRepository {

    private ArrayList<Book> books;
    private BookAdapter book_adapter;

    private static RemoteBookRepository instance;

    private RemoteBookRepository() {
        books = new ArrayList<Book>();
    }

    public static RemoteBookRepository getInstance() {
        if (instance == null) {
            instance = new RemoteBookRepository();
        }
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

    @Override
    public void sort(ArrayList<Book> books) {
        Collections.sort(books, new Comparator<Book>() {
                    @Override
                    public int compare(final Book book_1, final Book book_2) {
                        return book_1.getTitle().compareTo(book_2.getTitle());
                    }
                }
        );
    }

    @Override
    public ArrayList<Book> search(String newText) {
        ArrayList<Book> found_book = new ArrayList<Book>();
        if (newText != null && !newText.isEmpty()) {
            for (Book book : books) {
                if (book.getTitle().contains(newText)) {
                    found_book.add(book);
                }  /* else if (Integer.valueOf(newText) == book.getYear()) {
                          found_book.add(book);
                        } */
            }
            sort(found_book);
            return found_book;
        } else {
            ArrayList<Book> book = books;
            sort(book);
            return book;
        }
    }


    public class BookFetcherTask extends AsyncTask<Void, Void, ArrayList<Book>> {
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
