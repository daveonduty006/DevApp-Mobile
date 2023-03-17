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

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.File;;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MyCategSelectListener {

    private final HashMap<String,Integer> CATEGORIES = new HashMap(Map.of(
            "Abdominaux", R.drawable.abdominaux,
            "Avant-Bras", R.drawable.avant_bras,
            "Biceps", R.drawable.biceps,
            "Cuisses", R.drawable.cuisses,
            "Dos", R.drawable.dos,
            "Épaules", R.drawable.epaules,
            "Mollets", R.drawable.mollets,
            "Pectoraux", R.drawable.pectoraux,
            "Triceps", R.drawable.triceps
    ));

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar myToolbar;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExerciceDbHelper exerciceDbHelper = new ExerciceDbHelper(this);
        setMyToolbar();
        try {
            setMyRecyclerView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setMyDrawerLayout();
    }

    private void setMyNavigationView() {
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                // Handle navigation view item clicks here
                if (id == R.id.email_nav_item) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:email@example.com")); // Replace with the actual email address
                    startActivity(Intent.createChooser(emailIntent, "Send email"));
                } else if (id == R.id.phone_nav_item) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:+1234567890")); // Replace with the actual phone number
                    startActivity(callIntent);
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
        myRecyclerView = findViewById(R.id.my_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        myRecyclerView.setLayoutManager(gridLayoutManager);
        //
        File directory = this.getFilesDir();
        int idDrawable;
        Drawable drawable;
        Bitmap bitmap;
        String fileName;
        File file;
        FileOutputStream fos;
        for(int i=0; i < CATEGORIES.size(); i++){
            idDrawable = CATEGORIES.get(CATEGORIES.keySet().toArray()[i]);
            drawable = ContextCompat.getDrawable(this, idDrawable);
            bitmap = ((BitmapDrawable) drawable).getBitmap();
            fileName = getResources().getResourceEntryName(idDrawable) + ".png"; // add file extension
            file = new File(directory, fileName);
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        }
        myRecyclerView.setAdapter(new MyCategListAdapter(getApplicationContext(), CATEGORIES, this));
    }

    private void setMyToolbar() {
        myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_bg, getTheme()));
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
            if (!this.getClass().getSimpleName().equals(MainActivity.class.getSimpleName())) {
                Intent mainActivityIntent = new Intent(this, MainActivity.class);
                mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainActivityIntent);
                finish();
            }
        }
        if (id == R.id.contact_tab || id == android.R.id.home) {
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
    public void onCategBtnClicked(String categorie) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("categorie", categorie);
        startActivity(intent);
    }
}