package com.example.examen3;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class RechercherContactDialogFragment extends DialogFragment {

    private Button fermerBtn;

    private EditText rechercheEditTxt;
    private EditText nomEditTxt;
    private EditText prenomEditTxt;
    private EditText telephoneEditTxt;

    private Contact contact;

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_rechercher_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nomEditTxt = view.findViewById(R.id.nom_contact_recherche_edittext);
        nomEditTxt.setClickable(false);
        prenomEditTxt = view.findViewById(R.id.prenom_contact_recherche_edittext);
        prenomEditTxt.setClickable(false);
        telephoneEditTxt = view.findViewById(R.id.telephone_contact_recherche_edittext);
        telephoneEditTxt.setClickable(false);
        //
        rechercheEditTxt = view.findViewById(R.id.recherche_edittext);
        rechercheEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String telephone = rechercheEditTxt.getText().toString();
                ContactDbHelper contactDbHelper = new ContactDbHelper(requireContext());
                Contact contactRecherche = contactDbHelper.getOneByTelephone(telephone);
                if(contactRecherche.getTelephone() != "") {
                    contact = contactRecherche;
                    nomEditTxt.setText(contact.getNom());
                    prenomEditTxt.setText(contact.getPrenom());
                    telephoneEditTxt.setText(contact.getTelephone());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                //
            }
        });
        //
        fermerBtn = view.findViewById(R.id.fermer_button);
        fermerBtn.setOnClickListener(v -> dismiss());
    }

}
