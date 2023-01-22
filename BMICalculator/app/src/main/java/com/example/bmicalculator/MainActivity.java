package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        TextView resultTxtView = findViewById(R.id.txt_view_result);
        Button calculateBtn = findViewById(R.id.btn_calculate);
        EditText weightEditTxt = findViewById(R.id.edit_txt_weight);
        EditText inchesEditTxt = findViewById(R.id.edit_txt_inches);
        EditText feetEditTxt = findViewById(R.id.edit_txt_feet);
        EditText ageEditTxt = findViewById(R.id.edit_txt_age);
        RadioButton femaleRadioBtn = findViewById(R.id.radio_btn_female);
        RadioButton maleRadioBtn = findViewById(R.id.radio_btn_male);
        //
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                               "calculateBtn works!",
                                Toast.LENGTH_LONG).show();
            }
        });
    }
}