package au.edu.jcu.cp3406.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class DisplayDataActivity extends AppCompatActivity {

    public static int DATA_REQUEST = 2;
    Toolbar dataToolbar;
    TextView incomeCategoryText;
    TextView expenseCategoryText;
    TextView incomeAmountText;
    TextView expenseAmountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        dataToolbar = findViewById(R.id.data_toolbar);
        setSupportActionBar(dataToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }

        // Retrieve the data from MainActivity
        Intent intent = getIntent();
        ArrayList<String> incomeCategories = intent.getStringArrayListExtra("incomeCategory");
        ArrayList<String> expenseCategories = intent.getStringArrayListExtra("expenseCategory");
        ArrayList<Integer> incomeAmounts = intent.getIntegerArrayListExtra("incomeAmount");
        ArrayList<Integer> expenseAmounts = intent.getIntegerArrayListExtra("expenseAmount");

        String incomeCategoryInput = "";
        String incomeAmountInput = "";
        incomeCategoryText = findViewById(R.id.income_categories_text);
        incomeAmountText = findViewById(R.id.income_amount_text);
        for (int i = 0; i < incomeCategories.size(); i++) {
            incomeCategoryInput = incomeCategoryInput + "\n" + incomeCategories.get(i);
            incomeAmountInput = incomeAmountInput + "\n" + incomeAmounts.get(i);
            incomeCategoryText.setText(incomeCategoryInput);
            incomeAmountText.setText(incomeAmountInput);
        }
        String expenseCategoryInput = "";
        String expenseAmountInput = "";
        expenseCategoryText = findViewById(R.id.expense_categories_text);
        expenseAmountText = findViewById(R.id.expense_amount_text);
        for (int i = 0; i < incomeCategories.size(); i++) {
            expenseCategoryInput = expenseCategoryInput + "\n" + expenseCategories.get(i);
            expenseAmountInput = expenseAmountInput + "\n" + expenseAmounts.get(i);
            expenseCategoryText.setText(expenseCategoryInput);
            expenseAmountText.setText(expenseAmountInput);
        }


        // Add the incomes, expenses and provide total profit/loss statement

        // Income
        int incomeTotalSum = 0;
        for (int i = 0; i < incomeAmounts.size(); i++) {
            incomeTotalSum += incomeAmounts.get(i);
        }

        // Expenses
        int expenseTotalSum = 0;
        for (int i = 0; i < expenseAmounts.size(); i++) {
            expenseTotalSum += expenseAmounts.get(i);
        }

        // Total
        int totalProfitLoss = incomeTotalSum - expenseTotalSum;
        TextView totalIncomeAmountText = findViewById(R.id.total_income_amount);
        totalIncomeAmountText.setText(String.valueOf(totalProfitLoss));


    }

}
