package au.edu.jcu.cp3406.assignment1;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    public static int SETTINGS_REQUEST = 2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    public void newExpenseCategoryClicked(View view) {
        EditText userInputExpense = findViewById(R.id.new_expense_category);
        String newExpenseCategory = userInputExpense.getText().toString();
        System.out.println(newExpenseCategory);
        userInputExpense.getText().clear();  // Clear the EditText when the 'Add' button is pressed

        // Toast Text
        Toast toast = Toast.makeText(this, "New Expense Category Added!", Toast.LENGTH_LONG);
        toast.show();
    }

    public void newIncomeCategoryClicked(View view) {
        EditText userInputIncome = findViewById(R.id.new_income_category);
        String newIncomeCategory = userInputIncome.getText().toString();
        System.out.println(newIncomeCategory);
        userInputIncome.getText().clear();  // Clear the EditText when the 'Add' button is pressed

        // Toast text
        Toast toast = Toast.makeText(this, "New Income Category Added!", Toast.LENGTH_LONG);
        toast.show();
    }

//    private void sendToast(String message, )


    public void doneClicked(View view) {
        finish();
    }

    // This method is called when the user presses the back button on the device
    @Override
    public void onBackPressed() {
        doneClicked(null);
    }


}
