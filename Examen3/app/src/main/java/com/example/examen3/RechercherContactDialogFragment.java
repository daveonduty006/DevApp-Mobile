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
            contact = bundle.getParcelable("contact");
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
        nomEditTxt.setText(contact.getNom());
        nomEditTxt.setClickable(false);
        prenomEditTxt = view.findViewById(R.id.prenom_contact_recherche_edittext);
        prenomEditTxt.setText(contact.getPrenom());
        prenomEditTxt.setClickable(false);
        telephoneEditTxt = view.findViewById(R.id.telephone_contact_recherche_edittext);
        telephoneEditTxt.setText(contact.getTelephone());
        telephoneEditTxt.setClickable(false);
        //
        fermerBtn = view.findViewById(R.id.fermer_button);
        fermerBtn.setOnClickListener(v -> dismiss());
    }

}
