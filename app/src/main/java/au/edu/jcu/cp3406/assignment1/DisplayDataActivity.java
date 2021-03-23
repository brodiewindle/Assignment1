package au.edu.jcu.cp3406.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class DisplayDataActivity extends AppCompatActivity {

    public static int DATA_REQUEST = 2;
    Toolbar dataToolbar;
    TextView text2;


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



        ArrayList<String> list = new ArrayList<>();

        list.add("Books");
        list.add("Newspapers");
        list.add("Magazines");


        Intent intent = getIntent();
        ArrayList<String> incomeCategories = intent.getStringArrayListExtra("incomeCategory");
        System.out.println(incomeCategories.toString());



        text2 = findViewById(R.id.categories_text);
        String stringToInput = "";

        for (int i = 0; i < list.size(); i++) {
            stringToInput = stringToInput + "\n" + list.get(i);
            text2.setText(stringToInput);

        }
        
    }

}
