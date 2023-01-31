package com.example.labo1spinners;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bumptech.glide.*;

public class MainActivity extends AppCompatActivity {

    private final String[] VILLES_QUEBEC = {"Montréal", "Québec", "Laval", "Longueuil"};
    private final String[] VILLES_ONTARIO = {"Toronto", "Ottawa", "Mississauga", "Brampton"};
    private final String[] VILLES_CB = {"Vancouver", "Surrey", "Burnaby", "Richmond"};

    private Spinner tableProvinces;
    private Spinner tableVilles;
    private ImageView imageProvince;

    private List<String> provinces;
    private String provinceChoisie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        setTableProvinces();
    }

    private void setImageProvince() {
        switch (provinceChoisie) {
            case "Québec":
                Glide.with(this).load(R.drawable.quebec).into(imageProvince);
                break;
            case "Ontario":
                Glide.with(this).load(R.drawable.ontario).fitCenter().into(imageProvince);
                break;
            case "Colombie-Britannique":
                Glide.with(this).load(R.drawable.bc).fitCenter().into(imageProvince);
                break;
            default:
                System.out.println("Oops");
        }
    }

    private void setTableVilles() {
        ArrayAdapter<String> dataAdapter;
        switch (provinceChoisie) {
            case "Québec":
                dataAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item,
                        VILLES_QUEBEC);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                tableVilles.setAdapter(dataAdapter);
                break;
            case "Ontario":
                dataAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item,
                        VILLES_ONTARIO);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                tableVilles.setAdapter(dataAdapter);
                break;
            case "Colombie-Britannique":
                dataAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item,
                        VILLES_CB);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                tableVilles.setAdapter(dataAdapter);
                break;
            default:
                System.out.println("Oops");
        }
    }

    private void setTableProvinces() {
        provinces = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.table_provinces)));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                provinces);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tableProvinces.setAdapter(dataAdapter);
        //
        tableProvinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //arg0.setSelection(1);
                provinceChoisie = (String) tableProvinces.getSelectedItem();
                setTableVilles();
                setImageProvince();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void setViews() {
        tableProvinces = findViewById(R.id.table_provinces);
        tableVilles = findViewById(R.id.table_villes);
        imageProvince = findViewById(R.id.flag_imgview);
    }

}