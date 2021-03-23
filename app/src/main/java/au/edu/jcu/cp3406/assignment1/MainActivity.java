package au.edu.jcu.cp3406.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int totalIncome;
    private int totalExpense;

    private ArrayList<String> incomeCategories;
    private ArrayList<String> expenseCategories;
    private final ArrayList<Integer> incomeAmounts = new ArrayList<>();
    private final ArrayList<Integer> expenseAmounts = new ArrayList<>();

    Spinner incomeSpinner;
    Spinner expenseSpinner;
    ArrayAdapter<String> incomeAdapter;
    ArrayAdapter<String> expenseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        // Make income spinner
        incomeSpinner = findViewById(R.id.income_category_spinner);
        incomeCategories = new ArrayList<>();
        incomeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, incomeCategories);
        incomeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        incomeSpinner.setAdapter(incomeAdapter);
        incomeSpinner.setOnItemSelectedListener(this);

        // Make expense spinner
        expenseSpinner = findViewById(R.id.expense_category_spinner);
        expenseCategories = new ArrayList<>();
        expenseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, expenseCategories);
        expenseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expenseSpinner.setAdapter(expenseAdapter);
        expenseSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        System.out.println("Menu Created");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Here, a switch case can be made if there are multiple menu items
        // We only have one, so we can just handle it
        System.out.println("Settings Pressed");
        return super.onOptionsItemSelected(item);
    }

    public void settingsClicked(MenuItem item) {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivityForResult(settingsIntent, SettingsActivity.SETTINGS_REQUEST);
    }


    // Add a new expense entry
    public void newExpenseClicked(View view) {
        EditText userInputExpense = findViewById(R.id.expense_amount);
        String newExpenseAmount = userInputExpense.getText().toString();
        System.out.println(newExpenseAmount);
        int expenseAmount = Integer.parseInt(newExpenseAmount);

        // Update the new expense total
        totalExpense = totalExpense + expenseAmount;

        // get the category it has been entered into
        // assign the new value into the correct position
        // update the array
        String expenseCategorySelected = expenseSpinner.getSelectedItem().toString();
        int index = expenseCategories.indexOf(expenseCategorySelected);
        expenseAmounts.add(index, totalExpense);

        userInputExpense.getText().clear();  // Clear the EditText when the 'Add' button is pressed
    }

    // Add a new income entry
    public void newIncomeClicked(View view) {
        EditText userInputIncome = findViewById(R.id.income_amount);
        String newIncomeAmount = userInputIncome.getText().toString();
        System.out.println(newIncomeAmount);
        int incomeAmount = Integer.parseInt(newIncomeAmount);

        // Update the new income total
        totalIncome = totalIncome + incomeAmount;

        String incomeCategorySelected = incomeSpinner.getSelectedItem().toString();
        int index = incomeCategories.indexOf(incomeCategorySelected);
        incomeAmounts.add(index, totalIncome);

        userInputIncome.getText().clear();  // Clear the EditText when the 'Add' button is pressed
    }


    private void sendToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);  // Only need the LENGTH_LONG
        toast.show();
    }

    public void seeDataClicked(View view) {

        Intent dataIntent = new Intent(this, DisplayDataActivity.class);
        dataIntent.putStringArrayListExtra("incomeCategory", incomeCategories);
        dataIntent.putStringArrayListExtra("expenseCategory", expenseCategories);
        dataIntent.putIntegerArrayListExtra("incomeAmount", incomeAmounts);
        dataIntent.putIntegerArrayListExtra("expenseAmount", expenseAmounts);
        startActivityForResult(dataIntent, DisplayDataActivity.DATA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // If statements for each activity
        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK & data != null) {
                System.out.println("From settings in MainActivity");
                String newIncomeCategory = data.getStringExtra("incomeCategory");
                String newExpenseCategory = data.getStringExtra("expenseCategory");

                incomeCategories.add(newIncomeCategory);
                incomeAmounts.add(0);
                incomeAdapter.notifyDataSetChanged();

                expenseCategories.add(newExpenseCategory);
                expenseAmounts.add(0);
                expenseAdapter.notifyDataSetChanged();

            }
        } else if (requestCode == DisplayDataActivity.DATA_REQUEST) {
            if (resultCode == RESULT_OK & data != null) {
                System.out.println("Sugmaaa");
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}