package com.example.examen2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Film> tabFilms;
    private Toolbar myToolbar;
    private EditText numEditTxt, titreEditTxt, classementEditTxt, categorieEditTxt;
    private Button enregistrerBtn, effacerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        setFormEnregViews();
    }

    private void enregistrer() {
        int numFilm = Integer.parseInt(numEditTxt.getText().toString());
        String titre = titreEditTxt.getText().toString();
        double classement = Double.parseDouble(classementEditTxt.getText().toString());
        String categorie = categorieEditTxt.getText().toString();
        Film nouveauFilm = new Film(numFilm, titre, classement, categorie);
        tabFilms.add(nouveauFilm);
        Toast.makeText(this, "Film "+numFilm+" enregistré", Toast.LENGTH_LONG).show();
        numEditTxt.setText("");
        titreEditTxt.setText("");
        classementEditTxt.setText("");
        categorieEditTxt.setText("");
    }

    private void rendreVisibleFormEnreg() {
        LinearLayout formLayout = findViewById(R.id.form_container);
        LinearLayout btnsLayout = findViewById(R.id.btns_container);
        formLayout.setVisibility(View.VISIBLE);
        btnsLayout.setVisibility(View.VISIBLE);
    }

    private void rechercher() {
        Intent intent = new Intent(this, RechercherActivity.class);
        intent.putParcelableArrayListExtra("films", tabFilms);
        startActivity(intent);
    }

    private void lister() {
        Intent intent = new Intent(this, ListerActivity.class);
        intent.putParcelableArrayListExtra("films", tabFilms);
        startActivity(intent);
    }

    private void charger() {
        tabFilms = new ArrayList<>();
        tabFilms.add(new Film(1125, "Le Dernier Empereur", 4.5, "Action"));
        tabFilms.add(new Film(1279, "Titanic", 5, "Drame"));
        tabFilms.add(new Film(1487, "Diner de Cons", 4, "Comedie"));
        tabFilms.add(new Film(2452, "Interstellaire", 5, "Drame"));
        tabFilms.add(new Film(3210, "Good Cop Bad Cop", 3.5, "Comedie"));
        tabFilms.add(new Film(4211, "Reine des Neiges", 4, "Enfants"));
        Toast.makeText(this, "Films chargés", Toast.LENGTH_SHORT).show();
    }

    private void setFormEnregViews() {
        enregistrerBtn = findViewById(R.id.enregistrer_btn);
        enregistrerBtn.setOnClickListener(this);
        effacerBtn = findViewById(R.id.effacer_btn);
        effacerBtn.setOnClickListener(this);
        numEditTxt = findViewById(R.id.num_edittxt);
        titreEditTxt = findViewById(R.id.titre_edittxt);
        classementEditTxt = findViewById(R.id.classement_edittxt);
        categorieEditTxt = findViewById(R.id.categorie_edittxt);
    }

    private void setToolbar() {
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        // Handle menu item clicks
        if (id == R.id.charger_tab) {
            charger();
            return true;
        } else if (id == R.id.enregistrer_tab) {
            rendreVisibleFormEnreg();
            return true;
        } else if (id == R.id.lister_tab) {
            lister();
            return true;
        } else if (id == R.id.rechercher_tab) {
            rechercher();
            return true;
        } else if (id == R.id.quitter_tab) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.enregistrer_btn:
                enregistrer();
            break;
            case R.id.effacer_btn:
                //
            break;
        }
    }
}