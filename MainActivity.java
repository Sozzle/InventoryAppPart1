package com.example.android.inventoryapppart1;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapppart1.data.BookContract;
import com.example.android.inventoryapppart1.data.BookDbHelper;
import com.example.android.inventoryapppart1.data.BookContract.BookEntry;

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
        displayDatabaseInfo();

    }

    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        BookDbHelper mDbHelper = new BookDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        //projection
        String[] projection = {
                BookEntry._ID,
                BookEntry.COLUMN_PRODUCT_NAME,
                BookEntry.COLUMN_PRICE,
                BookEntry.COLUMN_QUANTITY,
                BookEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                BookEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER
        };


        Cursor cursor = db.query(
                BookContract.BookEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        TextView displayView = (TextView) findViewById(R.id.text_view_book);

        try {

            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // inventory table in the database).

            displayView.setText("Number of rows in inventory database table: " + cursor.getCount());
            // Create a header in the Text View that looks like this:
            //
            // The inventory table contains <number of rows in Cursor> books.
            // _id - product - price - quantity - supplier name - supplier phone number
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The inventory table contains " + cursor.getCount() + " books.\n\n");
            displayView.append(BookEntry._ID + " - " +
                    BookEntry.COLUMN_PRODUCT_NAME + " - " +
                    BookEntry.COLUMN_PRICE + " - " +
                    BookEntry.COLUMN_QUANTITY + " - " +
                    BookEntry.COLUMN_PRODUCT_SUPPLIER_NAME + "-" +
                    BookEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER + "-");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);
            int productNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER);


            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(productNameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierPhoneNumber = cursor.getString(supplierPhoneNumberColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentProductName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplierName + "- " +
                        currentSupplierPhoneNumber));
            }

        } finally {
            cursor.close();
        }

    }

    private void insertBook() {

        // Create database helper

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();

        values.put(BookEntry.COLUMN_PRODUCT_NAME, "Warcraft");
        values.put(BookEntry.COLUMN_PRICE, "10");
        values.put(BookEntry.COLUMN_QUANTITY, "9");
        values.put(BookEntry.COLUMN_PRODUCT_SUPPLIER_NAME, "Waterstones");
        values.put(BookEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, "01389763344");

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
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu options from the res/menu/menu_editor.xml file.
            // This adds menu items to the app bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // User clicked on a menu option in the app bar overflow menu
            switch (item.getItemId()) {
                // Respond to a click on the "Save" menu option
                case R.id.action_save:
                    // Save book to database
                    insertBook();
                    displayDatabaseInfo();
                    return true;
                // Respond to a click on the "Delete" menu option
                case R.id.action_delete:
                    // Do nothing for now
                    return true;
                // Respond to a click on the "Up" arrow button in the app bar
                case android.R.id.home:
                    // Navigate back to parent activity (CatalogActivity)
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

