package com.example.android.inventoryapppart1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.android.inventoryapppart1.data.BookContract;
import com.example.android.inventoryapppart1.data.BookDbHelper;

public class MainActivity extends AppCompatActivity {
    BookDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onStart() {
        super.onStart();
        insertBook();
    }


    private void insertBook() {


        // Create database helper
        BookDbHelper mDbHelper = new BookDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();

        values.put(BookContract.BookEntry.PRODUCT_NAME, "Book");
        values.put(BookContract.BookEntry.PRICE, "10");
        values.put(BookContract.BookEntry.QUANTITY, "9");
        values.put(BookContract.BookEntry.PRODUCT_SUPPLIER_NAME, "supplier name");
        values.put(BookContract.BookEntry.PRODUCT_SUPPLIER_PHONE_NUMBER, "01389763344");

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(BookContract.BookEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving book", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Book saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    private Cursor dataReader() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        //projection
        String[] projection = {
                BookContract.BookEntry._ID,
                BookContract.BookEntry.PRODUCT_NAME,
                BookContract.BookEntry.PRICE,
                BookContract.BookEntry.QUANTITY,
                BookContract.BookEntry.PRODUCT_SUPPLIER_NAME,
                BookContract.BookEntry.PRODUCT_SUPPLIER_PHONE_NUMBER
        };
        Cursor cursor = db.query(
                BookContract.BookEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        try {
            Log.v("MainActivity", "it's working?" + cursor.getCount());
        } finally {
            cursor.close();
        }
        return cursor;
    }
}

