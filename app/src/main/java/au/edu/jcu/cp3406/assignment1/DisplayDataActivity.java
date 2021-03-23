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

        String incomeCategoryInput = "";
        String incomeAmountInput = "";
        incomeCategoryText = findViewById(R.id.income_categories_text);
        for (int i = 0; i < incomeCategories.size(); i++) {
            incomeCategoryInput = incomeCategoryInput + "\n" + incomeCategories.get(i);
            // TODO: Income Amount
            incomeCategoryText.setText(incomeCategoryInput);
        }
        String expenseCategoryInput = "";
        String expenseAmountInput = "";
        incomeCategoryText = findViewById(R.id.expense_categories_text);
        for (int i = 0; i < incomeCategories.size(); i++) {
            expenseCategoryInput = expenseCategoryInput + "\n" + expenseCategories.get(i);
            // TODO: Income Amount
            incomeCategoryText.setText(expenseCategoryInput);
        }
        
    }

}
