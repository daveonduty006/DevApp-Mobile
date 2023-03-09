package com.example.examen2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListerActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private Button retournerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister);
        setTableLayout();
        setButtonRetourner();
        processIncomingIntent();
    }

    private void construireRangee(Film unFilm) {
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

    private void processIncomingIntent() {
        ArrayList<Film> tabFilms = getIntent().getParcelableArrayListExtra("films");
        for(Film unFilm : tabFilms) {
            construireRangee(unFilm);
        }
    }

    private void setTableLayout() {
        tableLayout = findViewById(R.id.table_layout);
        tableLayout.setBackgroundResource(R.drawable.row_border);
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