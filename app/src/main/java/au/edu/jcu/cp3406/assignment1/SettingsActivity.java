package au.edu.jcu.cp3406.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    public static int SETTINGS_REQUEST = 3;

    public ArrayList<String> newIncomeCategories = new ArrayList<>();
    public ArrayList<String> newExpenseCategories = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    public void newIncomeCategoryClicked(View view) {
        String newIncomeCategory;
        EditText userInputIncome = findViewById(R.id.new_income_category);
        if (userInputIncome.getText().toString().trim().length() > 0) {
            newIncomeCategory = userInputIncome.getText().toString();
            newIncomeCategories.add(newIncomeCategory);
            userInputIncome.getText().clear();  // Clear the EditText when the 'Add' button is pressed
            sendToast("New Income Category Added!");
        } else {
            sendToast("Please enter a category");
        }

    }

    public void newExpenseCategoryClicked(View view) {
        String newExpenseCategory;
        EditText userInputExpense = findViewById(R.id.new_expense_category);
        if (userInputExpense.getText().toString().trim().length() > 0) {
            newExpenseCategory = userInputExpense.getText().toString();
            newExpenseCategories.add(newExpenseCategory);
            userInputExpense.getText().clear();  // Clear the EditText when the 'Add' button is pressed
            sendToast("New Expense Category Added!");
        } else {
            sendToast("Please enter a category");
        }
    }


    private void sendToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);  // Only need the LENGTH_LONG
        toast.show();
    }

    public void doneClicked(View view) {
        Intent intent = new Intent();
        intent.putExtra("incomeCategory", newIncomeCategories);
        intent.putExtra("expenseCategory", newExpenseCategories);
        intent.putExtras(intent);
        setResult(RESULT_OK, intent);  // Send the information back to MainActivity
        finish();
    }

    // This method is called when the user presses the back button on the device
    @Override
    public void onBackPressed() {
        doneClicked(null);
    }


}
