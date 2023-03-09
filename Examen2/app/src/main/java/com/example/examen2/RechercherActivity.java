package com.example.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RechercherActivity extends AppCompatActivity {

    private Spinner spinnerCateg;
    private TableLayout tableLayout;
    private Button retournerBtn;
    private List<String> categs;
    private ArrayList<Film> listeFilms;
    private String categChoisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechercher);
        processIncomingIntent();
        setTableLayout();
        setSpinner();
        setButtonRetourner();
    }

    private void setTable() {
        for(Film unFilm : listeFilms) {
            if(categChoisi.equals(unFilm.getCategorie())) {
                int numFilm = unFilm.getNumFilm();
                String titre = unFilm.getTitre();
                double classement = unFilm.getClassement();
                String categorie = unFilm.getCategorie();
                //
                LayoutInflater inflater = LayoutInflater.from(this);
                TableRow newRow = (TableRow) inflater.inflate(R.layout.table_row_layout, null);
                newRow.setBackgroundResource(R.drawable.row_border);
                //
                TextView colNum = newRow.findViewById(R.id.num_txtview);
                TextView colTitre = newRow.findViewById(R.id.titre_txtview);
                TextView colClassement = newRow.findViewById(R.id.classement_txtview);
                TextView colCategorie = newRow.findViewById(R.id.categorie_txtview);
                //
                colNum.setText(String.valueOf(numFilm));
                colTitre.setText(titre);
                colClassement.setText(String.valueOf(classement));
                colCategorie.setText(categorie);
                //
                tableLayout.addView(newRow);
            }
        }
    }

    private void videTable() {
        int childCount = tableLayout.getChildCount();
        for (int i = childCount - 1; i > 0; i--) { // start from index 1
            View child = tableLayout.getChildAt(i);
            if (child instanceof TableRow) {
                tableLayout.removeView(child); // remove the row
            }
        }
    }

    private void setSpinner() {
        spinnerCateg = findViewById(R.id.categ_spinner);
        categs = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.table_films)));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                categs);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCateg.setAdapter(dataAdapter);
        //
        spinnerCateg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                videTable();
                categChoisi = (String) spinnerCateg.getSelectedItem();
                setTable();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void setTableLayout() {
        tableLayout = findViewById(R.id.table_layout);
        tableLayout.setBackgroundResource(R.drawable.row_border);
    }

    private void processIncomingIntent() {
        listeFilms = new ArrayList<>();
        listeFilms = getIntent().getParcelableArrayListExtra("films");
    }

    private void setButtonRetourner() {
        retournerBtn = findViewById(R.id.retourner_btn);
        retournerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}