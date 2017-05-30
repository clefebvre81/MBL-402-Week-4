package edu.phoenix.mbl402.week4applt6720;

// Creator: Larissa Thompson
// Course: MBL/402
// Created Date: 5/29/2017
// App Description: Application uses a local database to track customers at a car lot. Uses the SQLite
//      abilities of Android.
// Class Description: Class to help with the creation and setup of the database

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name
    static final String DB_NAME = "CARDEALER.DB";

    // database version
    static final int DB_VERSION = 1;

    // Constructor for the help
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Call to the create query
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CustomerTable.CREATE_QUERY);
    }

    // Call to update the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CustomerTable.DROP_QUERY);
        db.execSQL(CustomerTable.CREATE_QUERY);
    }

}
