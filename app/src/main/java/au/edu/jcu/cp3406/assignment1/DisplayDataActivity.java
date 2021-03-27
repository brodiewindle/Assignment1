package au.edu.jcu.cp3406.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class DisplayDataActivity extends AppCompatActivity {
    ArrayList<String> incomeCategories;
    ArrayList<String> expenseCategories;

    Toolbar dataToolbar;
    TextView incomeCategoryText;
    TextView expenseCategoryText;
    TextView incomeAmountText;
    TextView expenseAmountText;
    TextView incomeTotalAmount;
    TextView expenseTotalAmount;

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
        incomeCategories = intent.getStringArrayListExtra("incomeCategory");
        expenseCategories = intent.getStringArrayListExtra("expenseCategory");
        ArrayList<Integer> incomeAmounts = intent.getIntegerArrayListExtra("incomeAmount");
        ArrayList<Integer> expenseAmounts = intent.getIntegerArrayListExtra("expenseAmount");

        // Write categories and totals to the screen
        writeIncomeData(incomeAmounts);
        writeExpenseData(expenseAmounts);
        writeTotalsData(incomeAmounts, expenseAmounts);
    }

    private void writeTotalsData(ArrayList<Integer> incomeAmounts, ArrayList<Integer> expenseAmounts) {
        // Income
        int incomeTotalSum = 0;
        for (int i = 0; i < incomeAmounts.size(); i++) {
            incomeTotalSum += incomeAmounts.get(i);
        }
        incomeTotalAmount = findViewById(R.id.total_income_amount);
        String incomeInput = "$" + incomeTotalSum;
        incomeTotalAmount.setText(incomeInput);

        // Expenses
        int expenseTotalSum = 0;
        for (int i = 0; i < expenseAmounts.size(); i++) {
            expenseTotalSum += expenseAmounts.get(i);
        }
        expenseTotalAmount = findViewById(R.id.total_expense_amount);
        String expenseInput = "$" + expenseTotalSum;
        expenseTotalAmount.setText(expenseInput);

        // Total Profit/Loss Statement
        int totalProfitLoss = incomeTotalSum - expenseTotalSum;
        TextView totalProLosAmountText = findViewById(R.id.total_profit_loss_amount);
        String profitLossInput = "$" + totalProfitLoss;
        totalProLosAmountText.setText(profitLossInput);
    }

    private void writeExpenseData(ArrayList<Integer> expenseAmounts) {
        String expenseCategoryInput = "";
        String expenseAmountInput = "";
        expenseCategoryText = findViewById(R.id.expense_categories_text);
        expenseAmountText = findViewById(R.id.expense_amount_text);
        if (expenseAmounts.isEmpty()) {
            sendToast("No Expense Entry");
        } else {
            for (int i = 0; i < expenseCategories.size(); i++) {
                expenseCategoryInput = expenseCategoryInput + "\n" + StringUtils.capitalize(expenseCategories.get(i));
                expenseAmountInput = expenseAmountInput + "\n" + "$" + expenseAmounts.get(i);
                expenseCategoryText.setText(expenseCategoryInput);
                expenseAmountText.setText(expenseAmountInput);
            }
        }
    }

    private void writeIncomeData(ArrayList<Integer> incomeAmounts) {
        String incomeCategoryInput = "";
        String incomeAmountInput = "";
        incomeCategoryText = findViewById(R.id.income_categories_text);
        incomeAmountText = findViewById(R.id.income_amount_text);
        if (incomeAmounts.isEmpty()) {
            sendToast("No Income Entry");
        } else {
            for (int i = 0; i < incomeCategories.size(); i++) {
                incomeCategoryInput = incomeCategoryInput + "\n" + StringUtils.capitalize(incomeCategories.get(i));
                incomeAmountInput = incomeAmountInput + "\n" + "$" + incomeAmounts.get(i);
                incomeCategoryText.setText(incomeCategoryInput);
                incomeAmountText.setText(incomeAmountInput);
            }
        }
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK);
        super.onBackPressed();
    }

    private void sendToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);  // Only need the LENGTH_SHORT
        toast.show();
    }

    /**
     * This function is required as the back button in the ActionBar will always return null for
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
