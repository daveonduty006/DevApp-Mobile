package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultTxtView;
    private Button calculateBtn;
    private EditText weightEditTxt;
    private EditText inchesEditTxt;
    private EditText feetEditTxt;
    private EditText ageEditTxt;
    private RadioButton femaleRadioBtn;
    private RadioButton maleRadioBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        resultTxtView = findViewById(R.id.txt_view_result);
        calculateBtn = findViewById(R.id.btn_calculate);
        weightEditTxt = findViewById(R.id.edit_txt_weight);
        inchesEditTxt = findViewById(R.id.edit_txt_inches);
        feetEditTxt = findViewById(R.id.edit_txt_feet);
        ageEditTxt = findViewById(R.id.edit_txt_age);
        femaleRadioBtn = findViewById(R.id.radio_btn_female);
        maleRadioBtn = findViewById(R.id.radio_btn_male);
    }

    private void setupButtonClickListener() {
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bodyMassIndex = calculateBtnHandler();
                int age = Integer.parseInt(ageEditTxt.getText().toString());
                if (age >= 18) {
                    displayResult(bodyMassIndex);
                } else {
                    displayGuidance(bodyMassIndex);
                }
            }
        });
    }

    private double calculateBtnHandler() {
        int feet = Integer.parseInt(feetEditTxt.getText().toString());
        int inches = Integer.parseInt(inchesEditTxt.getText().toString());
        double weight = Double.parseDouble(weightEditTxt.getText().toString());
        // conversion of height in imperial units to metric units
        int totalInches = (feet*12) + inches;
        double heightInMeters = totalInches * 0.0254;
        // conversion of weight in imperial unit to metric unit
        double weightInKilograms = weight * 0.4536;
        // return result from formula to find body mass index
        return weightInKilograms / (heightInMeters*heightInMeters);
    }

    private void displayResult(double bodyMassIndex) {
        DecimalFormat df = new DecimalFormat("0.00");
        String bodyMassIndexStr = df.format(bodyMassIndex);
        String fullResultStr;
        if (bodyMassIndex < 18.5) {
            fullResultStr = bodyMassIndexStr + " - You are underweight";
        } else if (bodyMassIndex > 25) {
            fullResultStr = bodyMassIndexStr + " - You are overweight";
        } else {
            fullResultStr = bodyMassIndexStr + " - You are at an healthy weight";
        }
        resultTxtView.setText(fullResultStr);
    }

    private void displayGuidance(double bodyMassIndex) {
        DecimalFormat df = new DecimalFormat("0.00");
        String bodyMassIndexStr = df.format(bodyMassIndex);
        String fullResultStr;
        if (maleRadioBtn.isChecked()) {
            fullResultStr = bodyMassIndexStr + " - As you are under 18, please consult with your " +
                    "                           doctor for the healthy range for boys";
        } else if (femaleRadioBtn.isChecked()) {
            fullResultStr = bodyMassIndexStr + " - As you are under 18, please consult with your " +
                    "                           doctor for the healthy range for girls";
        } else {
            fullResultStr = bodyMassIndexStr + " - As you are under 18, please consult with your " +
                    "                           doctor for an healthy range.";
        }
        resultTxtView.setText(fullResultStr);
    }

}