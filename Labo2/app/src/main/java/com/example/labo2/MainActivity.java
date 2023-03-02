package com.example.labo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity  {

    private Button listerBtn, categBtn, totalBtn;
    private ImageView leftIcon, rightIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setChildrenViews();
    }

    private void setChildrenViews() {
        listerBtn = findViewById(R.id.getAll_btn);
        categBtn = findViewById(R.id.getCateg_btn);
        //totalBtn = findViewById(R.id.getTotal_btn);
        leftIcon = findViewById(R.id.left_icon);
        rightIcon = findViewById(R.id.right_icon);
    }

}