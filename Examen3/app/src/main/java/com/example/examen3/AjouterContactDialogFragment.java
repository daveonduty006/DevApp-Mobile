package com.example.examen3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class AjouterContactDialogFragment extends DialogFragment {

    private Button ajouterBtn;
    private Button annulerBtn;
    private EditText nomEditTxt;
    private EditText prenomEditTxt;
    private EditText telephoneEditTxt;

    private ArrayList<Contact> contacts;
    private MyContactListAdapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the variables passed from the hosting activity
        Bundle bundle = getArguments();
        if (bundle != null) {
            contacts = bundle.getParcelableArrayList("contacts");
            myAdapter = bundle.getParcelable("adapter");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_ajouter_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //
        nomEditTxt = view.findViewById(R.id.nom_contact_edittext);
        prenomEditTxt = view.findViewById(R.id.prenom_contact_edittext);
        telephoneEditTxt = view.findViewById(R.id.telephone_contact_edittext);
        //
        ajouterBtn = view.findViewById(R.id.creer_contact_button);
        ajouterBtn.setOnClickListener(v -> ajouterContact());
        annulerBtn = view.findViewById(R.id.annuler_button);
        annulerBtn.setOnClickListener(v -> dismiss());
    }

    private void ajouterContact() {
        String nom = nomEditTxt.getText().toString();
        String prenom = prenomEditTxt.getText().toString();
        String telephone = telephoneEditTxt.getText().toString();
        if(!nom.isEmpty() && !prenom.isEmpty() && !telephone.isEmpty()) {
            Contact contact = new Contact(-1, nom, prenom, telephone);
            ContactDbHelper contactDbHelper = new ContactDbHelper(requireContext());
            contactDbHelper.addOne(contact);
            //
            modifierRecyclerView(contact);
            //
            dismiss();
            Toast.makeText(requireContext(), "Contact créé", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Tous les champs doivent remplis", Toast.LENGTH_SHORT).show();
        }
    }

    private void modifierRecyclerView(Contact contact) {
        contacts.add(contact);
        myAdapter.notifyItemInserted(contacts.size() - 1);
    }

}
