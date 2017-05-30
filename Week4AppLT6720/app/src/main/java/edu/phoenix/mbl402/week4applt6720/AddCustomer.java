package edu.phoenix.mbl402.week4applt6720;

// Creator: Larissa Thompson
// Course: MBL/402
// Created Date: 5/29/2017
// App Description: Application uses a local database to track customers at a car lot. Uses the SQLite
//      abilities of Android.
// Class Description: Activity to add a new customer. Has validation methods in place

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCustomer extends Activity{

    // UI Buttons and Text Fields
    private Button addButton;
    private EditText firstNameText;
    private EditText lastNameText;
    private EditText carMakeText;
    private EditText carModelText;
    private EditText carCostText;

    // Database Manager
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        // All of the edit text fields
        firstNameText = (EditText) findViewById(R.id.edit_first);
        lastNameText = (EditText) findViewById(R.id.edit_last);
        carMakeText = (EditText) findViewById(R.id.edit_make);
        carModelText = (EditText) findViewById(R.id.edit_model);
        carCostText = (EditText) findViewById(R.id.edit_cost);

        // The add button
        addButton = (Button)findViewById(R.id.add_customer);

        // Database manager and opening the database
        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        // Add button method. Validates entries and enters the new customer
        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                final String firstName = firstNameText.getText().toString();
                final String lastName = lastNameText.getText().toString();
                final String carMake = carMakeText.getText().toString();
                final String carModel = carModelText.getText().toString();
                try {
                    final double cost = Double.parseDouble(carCostText.getText().toString());

                    if (firstName.length()>0 && lastName.length()>0 && carMake.length()>0&&
                            carModel.length()>0 && cost > -1) {

                        Customer newCustomer = new Customer(firstName, lastName, carMake, carModel, cost);
                        databaseManager.addCustomer(newCustomer);

                        Intent main = new Intent(AddCustomer.this, MainMenu.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(main);
                    }else {
                        Toast.makeText(AddCustomer.this,
                                "Please enter values for each field",
                                Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(AddCustomer.this,
                            "Please enter values for each field",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
