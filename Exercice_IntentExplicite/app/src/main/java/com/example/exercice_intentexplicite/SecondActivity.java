package com.example.exercice_intentexplicite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView nomTxtView, prenomTxtView, ageTxtView, sexeTxtView;
    private Button retournerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        setActivityViews();
        processIncomingIntent();
    }

    private void processIncomingIntent() {
        Bundle bundle = getIntent().getExtras();
        Personne personne = bundle.getParcelable("personne");
        String nom = personne.getNom();
        String prenom = personne.getPrenom();
        int age = personne.getAge();
        String sexe = personne.getSexe();
        //
        nomTxtView.setText(nom);
        prenomTxtView.setText(prenom);
        ageTxtView.setText(String.valueOf(age));
        sexeTxtView.setText(sexe);
    }

    private void setActivityViews() {
        nomTxtView = findViewById(R.id.nom_txtview);
        prenomTxtView = findViewById(R.id.prenom_txtview);
        ageTxtView = findViewById(R.id.age_txtview);
        sexeTxtView = findViewById(R.id.sexe_txtview);
        retournerBtn = findViewById(R.id.retourner_btn);
        retournerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.retourner_btn:
                finish();
            break;
        }
    }
}
