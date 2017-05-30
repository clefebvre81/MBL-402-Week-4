package edu.phoenix.mbl402.week4applt6720;

// Creator: Larissa Thompson
// Course: MBL/402
// Created Date: 5/29/2017
// App Description: Application uses a local database to track customers at a car lot. Uses the SQLite
//      abilities of Android.
// Class Description: This class is the main activity of the app. Has a list of the customers, an
//      add button to add more. Clicking on the customer will bring up their information for edit or deletion.

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    // UI Elements
    private Button addButton;
    private ListView customerListView;

    // Adapter for the Database information
    private SimpleCursorAdapter adapter;

    // Call to the Database Manager Class
    private DatabaseManager databaseManager;

    // An array of the table columns
    final String[] from = new String[]{
            CustomerTable._ID, CustomerTable.FIRST_NAME, CustomerTable.LAST_NAME, CustomerTable.CAR_MAKE,
            CustomerTable.CAR_MODEL, CustomerTable.COST
    };
    // Array of the UI elements each column should map to
    final int[] to = new int[]{R.id.txt_id, R.id.txt_first_name, R.id.txt_last_name, R.id.txt_make, R.id.txt_model, R.id.txt_cost};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Opening Database connection
        databaseManager = new DatabaseManager(this);
        databaseManager.open();
        Cursor cursor = databaseManager.fetch();

        //Setting up the list view. Has a default if list is empty
        customerListView = (ListView) findViewById(R.id.lv_customers);
        customerListView.setEmptyView(findViewById(R.id.empty));

        // Setting up the SimpleCursorAdapter for the list view
        adapter = new SimpleCursorAdapter(this, R.layout.customer_row, cursor,
                from, to, 0);
        adapter.notifyDataSetChanged();

        // Setting adapter to listview
        customerListView.setAdapter(adapter);

        customerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

                // Marking each text view on the row
                TextView idTextView = (TextView) view.findViewById(R.id.txt_id);
                TextView firstTextView = (TextView) view.findViewById(R.id.txt_first_name);
                TextView lastTextView = (TextView) view.findViewById(R.id.txt_last_name);
                TextView makeTextView = (TextView) view.findViewById(R.id.txt_make);
                TextView modelTextView = (TextView) view.findViewById(R.id.txt_model);
                TextView costTextView = (TextView) view.findViewById(R.id.txt_cost);

                // Getting the values to pass them to the next activity
                String id = idTextView.getText().toString();
                String firstName = firstTextView.getText().toString();
                String lastName = lastTextView.getText().toString();
                String carMake = makeTextView.getText().toString();
                String carModel = modelTextView.getText().toString();
                String cost = costTextView.getText().toString();

                // Passing the row values to the viewing page
                Intent viewCustomer = new Intent(getApplicationContext(), ViewCustomer.class);
                viewCustomer.putExtra("id", id);
                viewCustomer.putExtra("firstName", firstName);
                viewCustomer.putExtra("lastName", lastName);
                viewCustomer.putExtra("carMake", carMake);
                viewCustomer.putExtra("carModel", carModel);
                viewCustomer.putExtra("carCost", cost);

                // Starting the View Customer Activity
                startActivity(viewCustomer);
            }
        });

        // Setting up the Add button and actions
        addButton = (Button) findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newCustomer = new Intent(MainMenu.this, AddCustomer.class);
                startActivity(newCustomer);
            }
        });
    }

    // Setting up the Action Bar menu and actions
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_about) {
            Toast.makeText(MainMenu.this,
                    R.string.main_menu_desc,
                    Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_info) {
            Toast.makeText(MainMenu.this,
                    R.string.program_credits,
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
