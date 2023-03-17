package com.example.projetexercices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    private Exercice exercice;
    private MyExpandableListAdapter myExpandableListAdapter;
    private List<String> listGroup;
    private HashMap<String, List<String>> listItem;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar myToolbar;
    private ExpandableListView expandableListView;
    private TextView selectedExerciceTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setMyToolbar();
        setMyDrawerLayout();
        setIntentProcessing();
        setExpandableList();
    }

    private void initData() {
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();
        listGroup.add("Vidéo de l'exercice");
        listGroup.add("Instructions");
        //
        String videoLink = exercice.getUrlVideo();
        String videoId = videoLink.substring(videoLink.indexOf("v=") + 2); // Extract the video ID
        String embedLink = "https://www.youtube.com/embed/" + videoId; // Construct the embed link
        //
        List<String> group1 = new ArrayList<>();
        group1.add(embedLink);
        List<String> group2 = new ArrayList<>();
        group2.add(exercice.getInstructions());
        //
        listItem.put(listGroup.get(0), group1);
        listItem.put(listGroup.get(1), group2);
    }

    private void setExpandableList() {
        expandableListView = findViewById(R.id.my_expandable_listview);
        initData();
        myExpandableListAdapter = new MyExpandableListAdapter(this, listGroup, listItem);
        expandableListView.setAdapter(myExpandableListAdapter);
    }

    private void setIntentProcessing() {
        exercice = getIntent().getParcelableExtra("exercice");
        selectedExerciceTxtView = findViewById(R.id.selected_exercice_txtview);
        selectedExerciceTxtView.setText(exercice.getNom());
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

    private void setMyDrawerLayout() {
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setMyNavigationView();
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

}