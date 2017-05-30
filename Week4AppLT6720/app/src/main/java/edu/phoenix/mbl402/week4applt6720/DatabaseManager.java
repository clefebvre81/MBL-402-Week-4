package edu.phoenix.mbl402.week4applt6720;

// Creator: Larissa Thompson
// Course: MBL/402
// Created Date: 5/29/2017
// App Description: Application uses a local database to track customers at a car lot. Uses the SQLite
//      abilities of Android.
// Class Description: Class to manage all the database calles including opening, close, creations, and updating
//      Contains the method to CRUD the customer table.

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    // Calling the Database Helper Class
    private DatabaseHelper helper;

    // Application Context
    private Context context;

    // The database
    private SQLiteDatabase database;

    // Constructor for the Manager
    public DatabaseManager(Context c) {
        context = c;
    }

    // Opening the Database
    public DatabaseManager open() throws SQLException {
        helper = new DatabaseHelper(context);
        database = helper.getWritableDatabase();
        return this;
    }

    // Method to close the Database
    public void closeDB() {
        helper.close();
    }

    // Method to add customers
    public void addCustomer(Customer customer) {
        ContentValues values = new ContentValues();
        values.put(CustomerTable.FIRST_NAME, customer.getFirstName());
        values.put(CustomerTable.LAST_NAME, customer.getLastName());
        values.put(CustomerTable.CAR_MAKE, customer.getCarMake());
        values.put(CustomerTable.CAR_MODEL, customer.getCarModel());
        values.put(CustomerTable.COST, customer.getCarCost());
        database.insert(CustomerTable.TABLE_NAME, null, values);
    }

    // Method to fetch the database information
    public Cursor fetch() {
        String[] columns = new String[] {CustomerTable._ID, CustomerTable.FIRST_NAME, CustomerTable.LAST_NAME, CustomerTable.CAR_MAKE,
                CustomerTable.CAR_MODEL,CustomerTable.COST};
        // Querying for all the data
        Cursor cursor = database.query(CustomerTable.TABLE_NAME, columns, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Method to update a customer
    public int updateCustomer(long _id, Customer customer) {
        ContentValues values = new ContentValues();
        values.put(CustomerTable.FIRST_NAME, customer.getFirstName());
        values.put(CustomerTable.LAST_NAME, customer.getLastName());
        values.put(CustomerTable.CAR_MAKE, customer.getCarMake());
        values.put(CustomerTable.CAR_MODEL, customer.getCarModel());
        values.put(CustomerTable.COST, customer.getCarCost());
        int i = database.update(CustomerTable.TABLE_NAME, values, CustomerTable._ID + " = " + _id, null);
        return i;
    }

    // Method to delete a customer
    public void deleteCustomer(long _id) {
        database.delete(CustomerTable.TABLE_NAME, CustomerTable._ID + "=" + _id, null);
    }

}
