package com.example.projetexercices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondActivity extends AppCompatActivity implements MyExerciceSelectListener, MyModifierSelectListener, MyDeleteSelectListener {

    private ExerciceDbHelper exerciceDbHelper;
    private List<Exercice> exercices;
    private String categorie;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar myToolbar;
    private TextView selectedCategTxtView;
    private RecyclerView myRecyclerView;
    private FloatingActionButton floatAddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setIntentProcessing();
        setMyToolbar();
        try {
            setMyRecyclerView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setMyDrawerLayout();
        setFloatingAddBtn();
    }

    private void setFloatingAddBtn() {
        floatAddBtn = findViewById(R.id.add_exercice_btn);
        floatAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreerExerciceDialogFragment dialogFragment = new CreerExerciceDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "CreerExerciceDialogFragment");
            }
        });
    }

    private void setMyNavigationView() {
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                // Handle navigation view item clicks here
                if (id == R.id.nav_item1) {
                    Toast.makeText(SecondActivity.this, "navItem1", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_item2) {
                    // Handle item 2 click
                } else if (id == R.id.nav_setting1) {
                    // Handle setting 1 click
                } else if (id == R.id.nav_setting2) {
                    // Handle setting 2 click
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void setMyDrawerLayout() {
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setMyNavigationView();
    }

    private void setMyRecyclerView() throws IOException {
        myRecyclerView = findViewById(R.id.my_ex_recyclerView);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setAdapter(new MyExerciceListAdapter(getApplicationContext(), exercices, this, this, this));
    }

    private void setIntentProcessing() {
        exerciceDbHelper = new ExerciceDbHelper(this);
        categorie = getIntent().getStringExtra("categorie");
        //
        selectedCategTxtView = findViewById(R.id.selected_categ_txtview);
        selectedCategTxtView.setText(categorie);
        //
        exercices = new ArrayList<>();
        exercices = exerciceDbHelper.getAllByCateg(categorie);
    }

    private void setMyToolbar() {
        myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_bg, getTheme()));
        setSupportActionBar(myToolbar);
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
        if (id == R.id.listerCateg_tab) {
            Toast.makeText(this, "allo", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == android.R.id.home) {
            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onExerciceBtnClicked(Exercice exercice) {
        Toast.makeText(this, exercice.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSupprimerImgBtnClicked(Exercice exercice, int position) {
        ExerciceDbHelper exerciceDbHelper1 = new ExerciceDbHelper(this);
        exerciceDbHelper1.deleteOne(exercice.getId());
        finish();
    }

    @Override
    public void onModifierImgBtnClicked(Exercice exercice) {
    }
}