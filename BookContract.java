package com.example.android.inventoryapppart1.data;

import android.provider.BaseColumns;

public class BookContract {

    public BookContract(){

    }

    public static final class BookEntry implements BaseColumns {

    public static final String TABLE_NAME = "Inventory";

    public final static String _ID = "BaseColumns";


        public final static String PRODUCT_NAME = "product";

        public final static String PRICE = "price";

        public final static String QUANTITY = "quantity";

        public final static String PRODUCT_SUPPLIER_NAME = "supplier name";

        public final static String PRODUCT_SUPPLIER_PHONE_NUMBER = "supplier phone";
}}
