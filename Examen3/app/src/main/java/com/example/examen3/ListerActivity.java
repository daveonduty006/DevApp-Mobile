package com.example.examen3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
        //
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Chercher par #téléphone...");
        //
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String telephone = query;
                ContactDbHelper contactDbHelper = new ContactDbHelper(getApplicationContext());
                Contact contactRecherche = contactDbHelper.getOneByTelephone(telephone);
                if(contactRecherche != null) {
                    RechercherContactDialogFragment rechercherDialogFragment = new RechercherContactDialogFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("contact", contactRecherche);
                    rechercherDialogFragment.setArguments(bundle);
                    rechercherDialogFragment.show(getSupportFragmentManager(), "RechercherContactDialogFragment");
                }else {
                    Toast.makeText(getApplicationContext(), "Numéro de téléphone inconnu", Toast.LENGTH_LONG).show();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                // Perform search action as the user types in the search bar
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        // Handle menu item clicks
        if (id == R.id.action_add) {
            AjouterContactDialogFragment ajouterDialogFragment = new AjouterContactDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("contacts", (ArrayList) contacts);
            bundle.putParcelable("adapter", myAdapter);
            ajouterDialogFragment.setArguments(bundle);
            ajouterDialogFragment.show(getSupportFragmentManager(), "AjouterContactDialogFragment");
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
        phoneNumber.replace("-", "");
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:+"+phoneNumber)); // Replace with the actual phone number
        startActivity(callIntent);
    }
}