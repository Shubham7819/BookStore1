package com.example.android.bookstore1.data;

import android.provider.BaseColumns;

public final class BooksContract {

    private BooksContract(){
    }

    public static final class BooksEntry implements BaseColumns{
        public final static String TABLE_NAME = "books";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRODUCT_NAME = "name";
        public final static String COLUMN_PRODUCT_PRICE = "price";
        public final static String COLUMN_PRODUCT_QUANTITY = "quantity";
        public final static String COLUMN_SUPPLIER_NAME = "supplier";
        public final static String COLUMN_SUPPLIER_PHONE = "number";
    }

}
