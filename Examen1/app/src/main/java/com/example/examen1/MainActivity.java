package com.example.examen1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final private double FRAIS_COUR = 25;

    private TextView dateTxtView;
    private RadioGroup typeRouteRadioGrp;
    private EditText limiteEditTxt;
    private EditText vitesseEditTxt;
    private TextView resultatTxtView;
    private Button calculerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        setDate();
    }

    private void calculerAmende(String typeRoute, int limiteVitesse, int vitesse){
        boolean fraisGrave = Math.abs(vitesse - limiteVitesse) >= 25 ? true : false;
        int fraisTypeRoute = typeRoute.equals("Municipal") ? 15 : 20;
        int nbKilometresOffense = Math.abs(vitesse - limiteVitesse);
        double montantFrais = 0;
        if(fraisGrave){
            montantFrais = nbKilometresOffense * 20 + FRAIS_COUR;
        }else{
            montantFrais = nbKilometresOffense * fraisTypeRoute + FRAIS_COUR;
        }
        resultatTxtView.setText(String.valueOf(montantFrais));
    }

    private void recupererInputs() {
        // TYPE ROUTE
        int checkedBtnId = typeRouteRadioGrp.getCheckedRadioButtonId();
        RadioButton checkedBtn = (RadioButton) findViewById(checkedBtnId);
        String typeRoute = checkedBtn.getText().toString();
        // LIMITE VITESSE
        int limiteVitesse = Integer.parseInt(limiteEditTxt.getText().toString());
        // VITESSE
        int vitesse = Integer.parseInt(vitesseEditTxt.getText().toString());
        calculerAmende(typeRoute, limiteVitesse, vitesse);
    }

    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(currentDate);
        dateTxtView.setText(formattedDate);
    }

    private void setViews() {
        dateTxtView = findViewById(R.id.date_txtview);
        typeRouteRadioGrp = findViewById(R.id.typeroute_radiogrp);
        limiteEditTxt = findViewById(R.id.limite_edittxt);
        vitesseEditTxt = findViewById(R.id.vitesse_edittxt);
        resultatTxtView = findViewById(R.id.result_txtview);
        calculerBtn = findViewById(R.id.calculate_btn);
        calculerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.calculate_btn:
                if(typeRouteRadioGrp.getCheckedRadioButtonId() != -1 &&
                   !limiteEditTxt.getText().toString().equals("") &&
                   !vitesseEditTxt.getText().toString().equals("")) {
                    recupererInputs();
                }else{
                    Toast.makeText(this, "Remplissez tout les champs svp",
                            Toast.LENGTH_LONG).show();
                }
        }
    }
}