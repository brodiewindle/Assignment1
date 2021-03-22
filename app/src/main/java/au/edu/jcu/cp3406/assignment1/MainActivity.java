package au.edu.jcu.cp3406.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private double totalIncome;
    private double totalExpense;

    ConstraintLayout expandableView;
    Button arrowBtn;
    CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        Button incomeButton = findViewById(R.id.income_category_text);
        registerForContextMenu(incomeButton);


        // Expandable View
//        expandableView = findViewById(R.id.supporting_text);
//        arrowBtn = findViewById(R.id.addBtn1);
//        cardView = findViewById(R.id.cardView);
//
//        arrowBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Add Button Pressed");
//                if (expandableView.getVisibility()==View.GONE){
//                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
//                    expandableView.setVisibility(View.VISIBLE);
//                    arrowBtn.setBackgroundResource(R.drawable.ic_add_button);
//                } else {
//                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
//                    expandableView.setVisibility(View.GONE);
//
//                }
//            }
//        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()) {
            case R.menu.income_category_menu:
                getMenuInflater().inflate(R.menu.income_category_menu, menu);
            case R.menu.main_menu:
                getMenuInflater().inflate(R.menu.main_menu, menu);
        }

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

        switch (item.getItemId()) {
            case R.id.category_1:
                System.out.println("Category 1 Selected");
                return true;
            case R.id.category_2:
                System.out.println("Category 2 Selected");
                return true;
            case R.id.category_3:
                System.out.println("Category 3 Selected");
                return true;
            case R.id.category_4:
                System.out.println("Category 4 Selected");
                return true;
            case R.id.category_5:
                System.out.println("Category 5 Selected");
                return true;
        }
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
        double expenseAmount = Double.parseDouble(newExpenseAmount);

        // Update the new expense total
        totalExpense = totalExpense + expenseAmount;
        System.out.println(totalExpense);
        userInputExpense.getText().clear();  // Clear the EditText when the 'Add' button is pressed
    }

    // Add a new income entry
    public void newIncomeClicked(View view) {
        EditText userInputIncome = findViewById(R.id.income_amount);
        String newIncomeAmount = userInputIncome.getText().toString();
        System.out.println(newIncomeAmount);
        double incomeAmount = Double.parseDouble(newIncomeAmount);

        // Update the new income total
        totalIncome = totalIncome + incomeAmount;
        System.out.println(totalIncome);
        userInputIncome.getText().clear();  // Clear the EditText when the 'Add' button is pressed
    }
}