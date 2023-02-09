package com.example.exerciceformulaire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText lastnameEditTxt;
    private EditText firstnameEditTxt;
    private RadioGroup genderRadioGrp;
    private Spinner jobSpinner;
    private CheckBox fulltimeCheckBox;
    private CheckBox parttimeCheckBox;
    private CheckBox oncallCheckBox;
    private CheckBox internCheckBox;
    private EditText commentEditTxt;
    private Button sendBtn;
    private Button cancelBtn;

    private List<String> jobs;
    private String selectedJob = "";
    private String answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        setJobSpinner();
    }

    private void printAnswers() {
        Toast toast = Toast.makeText(this, answers, Toast.LENGTH_LONG);
        toast.show();
    }

    private void grabAnswers() {
        // LAST NAME
        answers = "Nom: " + lastnameEditTxt.getText() + "\n" ;
        // FIRST NAME
        answers += "Prénom: " + firstnameEditTxt.getText() + "\n" ;
        // GENDER
        int checkedBtnId = genderRadioGrp.getCheckedRadioButtonId();
        RadioButton checkedBtn =(RadioButton) findViewById(checkedBtnId);
        answers += "Sexe: " + checkedBtn.getText() + "\n" ;
        // JOB
        answers += "Fonction: " + selectedJob + "\n" ;
        // TYPE OF WORK
        answers += "Type de Travail: ";
        String checkedTypeOfWork = "";
        if(fulltimeCheckBox.isChecked()){
            checkedTypeOfWork += "\n" + fulltimeCheckBox.getText();
        }if(parttimeCheckBox.isChecked()){
            checkedTypeOfWork += "\n" + parttimeCheckBox.getText();
        }if(oncallCheckBox.isChecked()){
            checkedTypeOfWork += "\n" + oncallCheckBox.getText();
        }if(internCheckBox.isChecked()){
            checkedTypeOfWork += "\n" + internCheckBox.getText();
        }
        answers += checkedTypeOfWork + "\n" ;
        // COMMENTS
        answers += "Commentaires: " + commentEditTxt.getText() + "\n" ;
        // PRINTING THE RESULTS
        printAnswers();
    }

    private boolean checkValidity() {
        boolean isOk = true;
        if(lastnameEditTxt.getText().toString().trim().length() == 0){
            isOk = false;
        }if(firstnameEditTxt.getText().toString().trim().length() == 0){
            isOk = false;
        }if(genderRadioGrp.getCheckedRadioButtonId() == -1){
            isOk = false;
        }if(selectedJob.equals("")){
            isOk = false;
        }if(!fulltimeCheckBox.isChecked() && !parttimeCheckBox.isChecked() &&
            !oncallCheckBox.isChecked() && !internCheckBox.isChecked()){
            isOk = false;
        }
        return isOk;
    }

    private void resetForm() {
        lastnameEditTxt.setText("");
        firstnameEditTxt.setText("");
        genderRadioGrp.clearCheck();
        selectedJob = "";
        fulltimeCheckBox.setChecked(false);
        parttimeCheckBox.setChecked(false);
        oncallCheckBox.setChecked(false);
        internCheckBox.setChecked(false);
        commentEditTxt.setText("");
    }

    private void setJobSpinner() {
        jobs = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.job_array)));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                jobs);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobSpinner.setAdapter(dataAdapter);
        jobSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedJob = (String) jobSpinner.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setViews() {
        lastnameEditTxt = findViewById(R.id.lastname_edittxt);
        firstnameEditTxt = findViewById(R.id.firstname_edittxt);
        genderRadioGrp = findViewById(R.id.gender_radiogrp);
        jobSpinner = findViewById(R.id.job_spinner);
        fulltimeCheckBox = findViewById(R.id.fulltime_checkbox);
        parttimeCheckBox = findViewById(R.id.parttime_checkbox);
        oncallCheckBox = findViewById(R.id.oncall_checkbox);
        internCheckBox = findViewById(R.id.intern_checkbox);
        commentEditTxt = findViewById(R.id.comment_edittxt);
        sendBtn = findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(this);
        cancelBtn = findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.send_btn:
                boolean isOk = checkValidity();
                if(isOk){
                    grabAnswers();
                }else{
                    Toast.makeText(this, "Remplissez tous les champs suivi d'un *",
                            Toast.LENGTH_LONG).show();
                }
            break;
            case R.id.cancel_btn:
                resetForm();
                Toast.makeText(this, "Formulaire réinitialisé",
                        Toast.LENGTH_LONG).show();
            break;
        }
    }
}