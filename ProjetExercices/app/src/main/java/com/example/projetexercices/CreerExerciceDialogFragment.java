package com.example.projetexercices;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreerExerciceDialogFragment extends DialogFragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri uri;
    private ImageView imgView;
    private Button choisirImageBtn;
    private Button creerBtn;
    private Button annulerBtn;

    private EditText cheminImageEditTxt;
    private EditText lienVideoEditTxt;
    private EditText nomEditTxt;
    private EditText descEditTxt;
    private EditText instrEditTxt;
    private Spinner spinner;

    private ArrayList<Exercice> exercices;
    private MyExerciceListAdapter myAdapter;
    private String categorieOrigine;
    private List<String> categs;
    private String categChoisi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the variables passed from the hosting activity
        Bundle bundle = getArguments();
        if (bundle != null) {
            exercices = bundle.getParcelableArrayList("exercices");
            myAdapter = bundle.getParcelable("adapter");
            categorieOrigine = bundle.getString("categorie");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_exercice_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //
        imgView = view.findViewById(R.id.exercice_imageview);
        cheminImageEditTxt = view.findViewById(R.id.chemin_image_exercice_edittext);
        lienVideoEditTxt = view.findViewById(R.id.lien_video_exercice_edittext);
        nomEditTxt = view.findViewById(R.id.nom_exercice_edittext);
        descEditTxt = view.findViewById(R.id.desc_exercice_textbox);
        instrEditTxt = view.findViewById(R.id.instr_exercice_textbox);
        //
        spinner = view.findViewById(R.id.categ_exercice_spinner);
        categs = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.array_categs)));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item,
                categs);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        int indexCategOrigine = categs.indexOf(categorieOrigine);
        spinner.setSelection(indexCategOrigine);
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
        choisirImageBtn = view.findViewById(R.id.choisir_exercice_image_button);
        choisirImageBtn.setOnClickListener(v -> openFileChooser());
        creerBtn = view.findViewById(R.id.creer_exercice_button);
        creerBtn.setOnClickListener(v -> creerExercice());
        annulerBtn = view.findViewById(R.id.annuler_button);
        annulerBtn.setOnClickListener(v -> dismiss());
    }

    private void modifierRecyclerView(Exercice exercice) {
        exercices.add(exercice);
        myAdapter.notifyItemInserted(exercices.size() - 1);
    }

    private void creerExercice() {
        String cheminImg = cheminImageEditTxt.getText().toString();
        String lienVideo = lienVideoEditTxt.getText().toString();
        String instructions = instrEditTxt.getText().toString();
        String description = descEditTxt.getText().toString();
        String categorie = categChoisi;
        String nom = nomEditTxt.getText().toString();
        if(!cheminImg.isEmpty() && !lienVideo.isEmpty() && !instructions.isEmpty() &&
           !description.isEmpty() && !categorie.isEmpty() && !nom.isEmpty()) {
            Exercice exercice = new Exercice(-1, nom, categorie, description, instructions, lienVideo, cheminImg);
            ExerciceDbHelper exerciceDbHelper = new ExerciceDbHelper(requireContext());
            exerciceDbHelper.addOne(exercice);
            //
            modifierRecyclerView(exercice);
            //
            dismiss();
            Toast.makeText(requireContext(), "Exercice "+exercice.getNom()+" créé", Toast.LENGTH_SHORT).show();
            /*
            if(getActivity() != null) {
                Toast.makeText(requireContext(), "Exercice "+exercice.getNom()+" créé", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
            */
        } else {
            Toast.makeText(requireContext(), "Tous les champs doivent remplis", Toast.LENGTH_SHORT).show();
        }
    }

    private void enregistrerImageDansStockageInterne(Uri uri, String uniqueFileName) throws IOException {
        InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
        File directory = requireContext().getFilesDir();
        File imageFile = new File(directory, uniqueFileName);
        //
        OutputStream outputStream = new FileOutputStream(imageFile);
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outputStream.close();
        //
        String cheminImage = imageFile.getAbsolutePath();
        cheminImageEditTxt.setText(cheminImage);
        imgView.setImageURI(uri);
    }

    private String donnerNomUniquePourImage() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String fileName = "image_" + dateFormat.format(new Date()) + ".png";
        return fileName;
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        fileChooserLauncher.launch(Intent.createChooser(intent, "Choisissez l'image du nouvel exercice"));
    }

    private ActivityResultLauncher<Intent> fileChooserLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                if (data != null && data.getData() != null) {
                    Uri uri = data.getData();
                    String uniqueFileName = donnerNomUniquePourImage();
                    try {
                        enregistrerImageDansStockageInterne(uri, uniqueFileName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    );

}
