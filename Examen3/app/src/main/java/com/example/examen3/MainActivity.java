package com.example.examen3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContactDbHelper contactDbHelper = new ContactDbHelper(this);
        Intent intent = new Intent(this, ListerActivity.class);
        startActivity(intent);
    }

}