package com.example.android.bookstore1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookstore1.data.BooksContract.BooksEntry;
import com.example.android.bookstore1.data.BooksDbHelper;

public class MainActivity extends AppCompatActivity {

    private BooksDbHelper mDbHelper;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button insertBtn = (Button) findViewById(R.id.insertDataBtn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        Button readBtn = (Button) findViewById(R.id.readDataBtn);
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryData();
            }
        });

        textView = (TextView) findViewById(R.id.text_view);

        mDbHelper = new BooksDbHelper(this);

    }

    private void insertData() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues row1 = new ContentValues();
        row1.put(BooksEntry.COLUMN_PRODUCT_NAME, "science");
        row1.put(BooksEntry.COLUMN_PRODUCT_PRICE, "125");
        row1.put(BooksEntry.COLUMN_PRODUCT_QUANTITY, "23");
        row1.put(BooksEntry.COLUMN_SUPPLIER_NAME, "xyz");
        row1.put(BooksEntry.COLUMN_SUPPLIER_PHONE, "4444444");

        long l = db.insert(BooksEntry.TABLE_NAME, null, row1);

        if (l == -1) {
            Toast.makeText(this, "there is some problem, data not inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "data inserted successfully", Toast.LENGTH_SHORT).show();
        }

    }

    private void queryData() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {BooksEntry._ID, BooksEntry.COLUMN_PRODUCT_NAME,
                BooksEntry.COLUMN_PRODUCT_PRICE, BooksEntry.COLUMN_PRODUCT_QUANTITY,
                BooksEntry.COLUMN_SUPPLIER_NAME, BooksEntry.COLUMN_SUPPLIER_PHONE};

        Cursor cursor = db.query(BooksEntry.TABLE_NAME, projection, null, null,
                null, null, null);

        try {
            int idColumnIndex = cursor.getColumnIndex(BooksEntry._ID);
            int productNameColumnIndex = cursor.getColumnIndex(BooksEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(BooksEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(BooksEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(BooksEntry.COLUMN_SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(BooksEntry.COLUMN_SUPPLIER_PHONE);
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(productNameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierPhoneNumber = cursor.getString(supplierPhoneNumberColumnIndex);
                textView.append(currentID + " - " + currentProductName + " - " + currentPrice + " - " +
                        currentQuantity + " - " + currentSupplierName + " - " + currentSupplierPhoneNumber + "\n");
            }
        } finally {
            cursor.close();
        }
    }

}
