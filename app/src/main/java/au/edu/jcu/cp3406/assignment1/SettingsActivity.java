package au.edu.jcu.cp3406.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    public static int SETTINGS_REQUEST = 2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    public void newExpenseClicked(View view) {
        EditText userInputExpense = findViewById(R.id.new_expense_category);
        String newExpenseCategory = userInputExpense.getText().toString();
        System.out.println(newExpenseCategory);
        userInputExpense.getText().clear();  // Clear the EditText when the 'Add' button is pressed

        // TODO: Add Toast text
    }

    public void newIncomeClicked(View view) {
        EditText userInputIncome = findViewById(R.id.new_income_category);
        String newIncomeCategory = userInputIncome.getText().toString();
        System.out.println(newIncomeCategory);
        userInputIncome.getText().clear();  // Clear the EditText when the 'Add' button is pressed

        // TODO: Add Toast text
    }


//    public void doneClicked(View view) {
//        EditText userInput = findViewById(R.id.userInput);
//        String text = userInput.getText().toString();
//        int speed = Integer.parseInt(text);
//
//        Intent intent = new Intent();
//        intent.putExtra("speed", speed);
//        setResult(RESULT_OK, intent);
//        finish();
//    }

}
