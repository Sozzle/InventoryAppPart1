package com.example.android.inventoryapppart1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.inventoryapppart1.data.BookContract.BookEntry;

public class BookDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = BookDbHelper.class.getSimpleName();
    /** Name of the database file */
    private static final String DATABASE_NAME = "Inventory.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link BookDbHelper}.
     *
     * @param context of the app
     */
    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the books table
            String SQL_CREATE_BOOKS_TABLE =  "CREATE TABLE " + BookContract.BookEntry.TABLE_NAME + " ("
                    + BookContract.BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + BookEntry.PRODUCT_NAME + " TEXT NOT NULL, "
                    + BookEntry.PRICE + " INTEGER NOT NULL, "
                    + BookEntry.QUANTITY + "INTEGER NOT NULL, "
                    + BookEntry.PRODUCT_SUPPLIER_NAME + "TEXT,"
                    + BookEntry.PRODUCT_SUPPLIER_PHONE_NUMBER + "  NOT NULL );";

            db.execSQL(SQL_CREATE_BOOKS_TABLE);

        }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
