package com.example.exercice_intentexplicite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nomEditTxt, prenomEditTxt, ageEditTxt, sexeEditTxt;
    private Button envoyerBtn, effacerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActivityViews();
    }

    private void envoyerIntent() {
        String nom = nomEditTxt.getText().toString();
        String prenom = prenomEditTxt.getText().toString();
        int age = Integer.parseInt(ageEditTxt.getText().toString());
        String sexe = sexeEditTxt.getText().toString();
        // On créé une instance de la classe "Personne" et l'ajoute à un conteneur de données (Bundle)
        Personne personne = new Personne(nom, prenom, age, sexe);
        Bundle bundle = new Bundle();
        bundle.putParcelable("personne", personne);
        // On créé un intent explicite pour lancer notre activité de destination et ajoute le bundle à l'intent
        Intent intent = new Intent(this, SecondActivity.class);
        intent.setAction("com.example.myapp.ACTION_NAME");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void setActivityViews() {
        nomEditTxt = findViewById(R.id.nom_edittxt);
        prenomEditTxt = findViewById(R.id.prenom_edittxt);
        ageEditTxt = findViewById(R.id.age_edittxt);
        sexeEditTxt = findViewById(R.id.sexe_edittxt);
        envoyerBtn = findViewById(R.id.envoyer_btn);
        envoyerBtn.setOnClickListener(this);
        effacerBtn = findViewById(R.id.effacer_btn);
        effacerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.envoyer_btn:
                envoyerIntent();
            break;
            case R.id.effacer_btn:
                //
            break;
        }
    }
}