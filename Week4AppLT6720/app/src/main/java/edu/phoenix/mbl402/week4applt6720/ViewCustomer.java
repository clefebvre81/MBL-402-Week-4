package edu.phoenix.mbl402.week4applt6720;

// Creator: Larissa Thompson
// Course: MBL/402
// Created Date: 5/29/2017
// App Description: Application uses a local database to track customers at a car lot. Uses the SQLite
//      abilities of Android.
// Class Description: This class is the viewing activity of current customers in the database.
//      allows for users to edit or delete an entry

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewCustomer extends Activity {

    // UI Buttons
    private Button updateButton;
    private Button deleteButton;

    // UI Text Fields
    private TextView customerID;
    private EditText firstNameText;
    private EditText lastNameText;
    private EditText carMakeText;
    private EditText carModelText;
    private EditText carCostText;

    // Long value of the Item ID from the database
    private long _id;

    // Database manager for access to DB methods
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);

        // Getting passed information
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        final String firstName = intent.getStringExtra("firstName");
        final String lastName= intent.getStringExtra("lastName");
        String carMake= intent.getStringExtra("carMake");
        String carModel = intent.getStringExtra("carModel");
        String carCost = intent.getStringExtra("carCost");

        // Getting the long value of the ID
        _id = Long.parseLong(id);

        // All of the edit text fields
        customerID = (TextView)findViewById(R.id.txt_id);
        firstNameText = (EditText) findViewById(R.id.edit_first);
        lastNameText = (EditText) findViewById(R.id.edit_last);
        carMakeText = (EditText) findViewById(R.id.edit_make);
        carModelText = (EditText) findViewById(R.id.edit_model);
        carCostText = (EditText) findViewById(R.id.edit_cost);

        // Setting text to passed values
        customerID.setText(id);
        firstNameText.setText(firstName);
        lastNameText.setText(lastName);
        carMakeText.setText(carMake);
        carModelText.setText(carModel);
        carCostText.setText(carCost);

        // The buttons
        updateButton = (Button)findViewById(R.id.button_update);
        deleteButton = (Button)findViewById(R.id.button_delete);

        // Database managers
        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        // Update button method, calls the database manager's update call
        // Validates the entries are all filled in by user
        updateButton.setOnClickListener(new View.OnClickListener() {
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
                        Customer updatedCustomer = new Customer(firstName, lastName, carMake, carModel, cost);
                        databaseManager.updateCustomer(_id, updatedCustomer);

                        Intent main = new Intent(ViewCustomer.this, MainMenu.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(main);
                    }else {
                        Toast.makeText(ViewCustomer.this,
                                "Please enter values for each field",
                                Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ViewCustomer.this,
                            "Please enter values for each field",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Delete method, calls the database manager's delete method
        // displays a confirmation
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ViewCustomer.this,
                        "Deleting " + firstName + " " + lastName,
                        Toast.LENGTH_SHORT).show();
               databaseManager.deleteCustomer(_id);

                Intent main = new Intent(ViewCustomer.this, MainMenu.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
            }
        });

    }

}