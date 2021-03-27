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


        if (savedInstanceState != null) {
            expenseCategories = savedInstanceState.getStringArrayList("expenseCategories");
            incomeCategories = savedInstanceState.getStringArrayList("incomeCategories");
        } else {
            expenseCategories = new ArrayList<>();
            incomeCategories = new ArrayList<>();
        }

        // Make income spinner with adapter to dynamically change it
        incomeSpinner = findViewById(R.id.income_category_spinner);
        incomeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, incomeCategories);
        incomeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        incomeSpinner.setAdapter(incomeAdapter);
        incomeSpinner.setOnItemSelectedListener(this);

        // Make expense spinner with adapter to dynamically change it
        expenseSpinner = findViewById(R.id.expense_category_spinner);
        expenseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, expenseCategories);
        expenseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expenseSpinner.setAdapter(expenseAdapter);
        expenseSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("incomeCategories", incomeCategories);
        savedInstanceState.putStringArrayList("expenseCategories", expenseCategories);
        savedInstanceState.putIntegerArrayList("incomeAmounts", incomeAmounts);
        savedInstanceState.putIntegerArrayList("expenseAmounts", expenseAmounts);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Here, a switch case can be made if there are multiple menu items
        // We only have one (the settings button), so we can just handle it
        return super.onOptionsItemSelected(item);
    }

    public void settingsClicked(MenuItem item) {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        // forResult as we want to return the new categories that are added
        startActivityForResult(settingsIntent, SettingsActivity.SETTINGS_REQUEST);
    }

    // Add a new expense entry
    public void newExpenseClicked(View view) {
        EditText userInputExpense = findViewById(R.id.expense_amount);
        String newExpenseAmount = userInputExpense.getText().toString();
        int expenseAmount;
        if (newExpenseAmount.isEmpty()) {
            sendToast("Please enter a valid quantity");
        } else {
            expenseAmount = Integer.parseInt(newExpenseAmount);
            // Update the new expense total
            totalExpense = totalExpense + expenseAmount;

            String expenseCategorySelected = expenseSpinner.getSelectedItem().toString();
            int index = expenseCategories.indexOf(expenseCategorySelected);

            if (index >= expenseAmounts.size()) {
                expenseAmounts.add(index, expenseAmount);
            } else {
                int lastValue = expenseAmounts.get(index);
                expenseAmounts.set(index, lastValue + expenseAmount);
            }
            userInputExpense.getText().clear();  // Clear the EditText when the 'Add' button is pressed
            sendToast("New Expense Entry Added!");
        }
    }

    // Add a new income entry
    public void newIncomeClicked(View view) {
        EditText userInputIncome = findViewById(R.id.income_amount);
        String newIncomeAmount = userInputIncome.getText().toString();
        int incomeAmount;
        if (newIncomeAmount.isEmpty()) {
            sendToast("Please enter a valid quantity");
        } else {
            incomeAmount = Integer.parseInt(newIncomeAmount);
            // Update the new income total
            totalIncome = totalIncome + incomeAmount;

            String incomeCategorySelected = incomeSpinner.getSelectedItem().toString();
            int index = incomeCategories.indexOf(incomeCategorySelected);

            if (index >= incomeAmounts.size()) {
                incomeAmounts.add(index, incomeAmount);
            } else {
                int lastValue = incomeAmounts.get(index);
                incomeAmounts.set(index, lastValue + incomeAmount);
            }
            userInputIncome.getText().clear();  // Clear the EditText when the 'Add' button is pressed
            sendToast("New Income Entry Added!");
        }
    }

    private void sendToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);  // Only need the LENGTH_LONG
        toast.show();
    }

    public void seeDataClicked(View view) {
        Intent dataIntent = new Intent(this, DisplayDataActivity.class); // Create intent in order to send data
        dataIntent.putStringArrayListExtra("incomeCategory", incomeCategories);
        dataIntent.putStringArrayListExtra("expenseCategory", expenseCategories);
        dataIntent.putIntegerArrayListExtra("incomeAmount", incomeAmounts);
        dataIntent.putIntegerArrayListExtra("expenseAmount", expenseAmounts);
        // We don't return anything from this activity, therefore there is no need for 'forResult'
        startActivity(dataIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK & data != null) {
                ArrayList<String> newIncomeCategory = data.getStringArrayListExtra("incomeCategory");
                ArrayList<String> newExpenseCategory = data.getStringArrayListExtra("expenseCategory");

                incomeCategories.addAll(newIncomeCategory);  // Add the new array of items to the spinner array
                incomeAmounts.add(0);  // Set the original value for it to zero
                incomeAdapter.notifyDataSetChanged();

                expenseCategories.addAll(newExpenseCategory);  // Add the new array of items to the spinner array
                expenseAmounts.add(0);  // Set the original value for it to zero
                expenseAdapter.notifyDataSetChanged();
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