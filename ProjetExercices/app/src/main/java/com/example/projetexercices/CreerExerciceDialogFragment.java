package com.example.projetexercices;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreerExerciceDialogFragment extends DialogFragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri uri;
    private ImageView imgView;
    private Button choisirImageBtn;
    private Button creerBtn;
    private Button annulerBtn;
    private EditText lienVideoEditTxt;
    private EditText nomEditTxt;
    private EditText descEditTxt;
    private EditText instrEditTxt;
    private Spinner spinner;

    private List<String> categs;
    private String categChoisi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_exercice_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgView = view.findViewById(R.id.exercice_imageView);
        lienVideoEditTxt = view.findViewById(R.id.lien_video_exercice_edittext);
        nomEditTxt = view.findViewById(R.id.nom_exercice_edittext);
        descEditTxt = view.findViewById(R.id.desc_exercice_textbox);
        instrEditTxt = view.findViewById(R.id.instr_exercice_textbox);
        //
        spinner = view.findViewById(R.id.categ_exercice_spinner);
        categs = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.array_categs)));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item,
                categs);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categChoisi = (String) spinner.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //
            }
        });
        //
        choisirImageBtn = view.findViewById(R.id.creer_exercice_button);
        choisirImageBtn.setOnClickListener(v -> openFileChooser());

        movieImageView = view.findViewById(R.id.movie_image_view);
        movieTitleEditText = view.findViewById(R.id.movie_title_edit_text);
        chooseImageButton = view.findViewById(R.id.choose_image_button);
        createMovieButton = view.findViewById(R.id.create_movie_button);

        chooseImageButton.setOnClickListener(v -> openFileChooser());
        createMovieButton.setOnClickListener(v -> createMovie());
    }

    private ActivityResultLauncher<Intent> fileChooserLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
                        Uri uri = data.getData();
                        // Use the selectedFileUri to perform further operations, e.g., read the file or display its content
                    }
                }
            });

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
