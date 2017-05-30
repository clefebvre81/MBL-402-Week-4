package edu.phoenix.mbl402.week4applt6720;

// Creator: Larissa Thompson
// Course: MBL/402
// Created Date: 5/29/2017
// App Description: Application uses a local database to track customers at a car lot. Uses the SQLite
//      abilities of Android.
// Class Description: Class to handle all of the Customer Table information

import android.provider.BaseColumns;

public class CustomerTable implements BaseColumns {

    // Table Name
    public static final String TABLE_NAME = "CUSTOMERS";

    // Column Names
    public static final String _ID = "_id";
    public static final String FIRST_NAME = "customer_first_name";
    public static final String LAST_NAME = "customer_last_name";
    public static final String CAR_MAKE = "car_make";
    public static final String CAR_MODEL = "car_model";
    public static final String COST = "cost";

    // Create Statement for the table
    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + "("+
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIRST_NAME +" TEXT, " +
            LAST_NAME +" TEXT, " +
            CAR_MAKE +" TEXT, " +
            CAR_MODEL +" TEXT, " +
            COST +" NUMERIC)";

    // Drop statement for the table
    public static final String DROP_QUERY = "DROP TABLE " + TABLE_NAME;

}
