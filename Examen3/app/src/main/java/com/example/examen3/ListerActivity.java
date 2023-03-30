package com.example.examen3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListerActivity extends AppCompatActivity implements MyPhoneSelectListener, MyDeleteSelectListener{

    private ContactDbHelper contactDbHelper;
    private List<Contact> contacts;

    private Toolbar myToolbar;
    private RecyclerView myRecyclerView;
    private MyContactListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister);
        setIntentProcessing();
        setMyToolbar();
        try {
            setMyRecyclerView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setIntentProcessing() {
        contactDbHelper = new ContactDbHelper(this);
        //
        contacts = new ArrayList<>();
        contacts = contactDbHelper.getAll();
    }

    private void setMyRecyclerView() throws IOException {
        myRecyclerView = findViewById(R.id.my_recyclerView);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyContactListAdapter(getApplicationContext(), contacts, this, this);
        myRecyclerView.setAdapter(myAdapter);
    }

    private void setMyToolbar() {
        myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_bg, getTheme()));
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
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
        if (id == R.id.lister_tab) {
        }
        if (id == R.id.ajouter_tab) {
            AjouterContactDialogFragment ajouterDialogFragment = new AjouterContactDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("contacts", (ArrayList) contacts);
            bundle.putParcelable("adapter", myAdapter);
            ajouterDialogFragment.setArguments(bundle);
            ajouterDialogFragment.show(getSupportFragmentManager(), "AjouterContactDialogFragment");
        }
        if (id == R.id.rechercher_tab) {
            RechercherContactDialogFragment rechercherDialogFragment = new RechercherContactDialogFragment();
            Bundle bundle = new Bundle();
            rechercherDialogFragment.setArguments(bundle);
            rechercherDialogFragment.show(getSupportFragmentManager(), "RechercherContactDialogFragment");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDeleteImgBtnClicked(Contact contact, int position) {
        ContactDbHelper contactDbHelper1 = new ContactDbHelper(this);
        contactDbHelper1.deleteOne(contact.getId());
        int indexContactSupprime = contacts.indexOf(contact);
        contacts.remove(indexContactSupprime);
        myAdapter.notifyItemRemoved(position);
        Toast.makeText(this, "Contact "+contact.getTelephone()+" supprimé", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPhoneImgBtnClicked(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:+"+phoneNumber)); // Replace with the actual phone number
        startActivity(callIntent);
    }
}