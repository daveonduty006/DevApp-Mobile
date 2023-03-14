package com.example.projetexercices;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class ExerciceDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "exercices.db";
    private static final int DATABASE_VERSION = 1;

    private final String EXERCICE_TABLE = "exercices";
    private final String COL_EXERCICE_ID = "_id";
    private final String COL_EXERCICE_NOM = "nom";
    private final String COL_EXERCICE_CATEG = "categorie";
    private final String COL_EXERCICE_DESC = "description";
    private final String COL_EXERCICE_INSTR = "instructions";
    private final String COL_EXERCICE_VIDEO = "urlVideo";
    private final String COL_EXERCICE_IMAGE = "cheminImage";

    public ExerciceDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ EXERCICE_TABLE + " ("
                + COL_EXERCICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_EXERCICE_NOM   + " TEXT, "
                + COL_EXERCICE_CATEG + " TEXT, "
                + COL_EXERCICE_DESC  + " TEXT, "
                + COL_EXERCICE_INSTR + " TEXT, "
                + COL_EXERCICE_VIDEO + " TEXT, "
                + COL_EXERCICE_IMAGE + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EXERCICE_TABLE);
        onCreate(db);
    }

    ///////// CRUD

    public List<Exercice> getAll() {
        List<Exercice> exercices = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + EXERCICE_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) { // we move the cursor over the first row of its attached result set
            do {
                long id = cursor.getLong(0);
                String nom = cursor.getString(1);
                String categorie = cursor.getString(2);
                String description = cursor.getString(3);
                String instructions = cursor.getString(4);
                String urlVideo = cursor.getString(5);
                String cheminImage = cursor.getString(6);
                //
                Exercice exercice = new Exercice(id, nom, categorie, description, instructions, urlVideo, cheminImage);
                exercices.add(exercice);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return exercices;
    }

    public List<Exercice> getAllByCateg(String categorie) {
        List<Exercice> exercices = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + EXERCICE_TABLE + " WHERE " + COL_EXERCICE_CATEG + " = '" + categorie + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) { // we move the cursor over the first row of its attached result set
            do {
                long id = cursor.getLong(0);
                String nom = cursor.getString(1);
                categorie = cursor.getString(2);
                String description = cursor.getString(3);
                String instructions = cursor.getString(4);
                String urlVideo = cursor.getString(5);
                String cheminImage = cursor.getString(6);
                //
                Exercice exercice = new Exercice(id, nom, categorie, description, instructions, urlVideo, cheminImage);
                exercices.add(exercice);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return exercices;
    }

    public Exercice getOne(long id) {
        Exercice exercice = new Exercice();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + EXERCICE_TABLE + " WHERE " + COL_EXERCICE_ID + " = '" + id + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) { // we move the cursor over the first row of its attached result set
            do {
                String nom = cursor.getString(1);
                String categorie = cursor.getString(2);
                String description = cursor.getString(3);
                String instructions = cursor.getString(4);
                String urlVideo = cursor.getString(5);
                String cheminImage = cursor.getString(6);
                //
                exercice.setId(id);
                exercice.setNom(nom);
                exercice.setCategorie(categorie);
                exercice.setDescription(description);
                exercice.setInstructions(instructions);
                exercice.setUrlVideo(urlVideo);
                exercice.setCheminImage(cheminImage);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return exercice;
    }

    public boolean addOne(Exercice exercice) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO " + EXERCICE_TABLE + " ("
                + COL_EXERCICE_NOM    + ","
                + COL_EXERCICE_CATEG  + ","
                + COL_EXERCICE_DESC   + ","
                + COL_EXERCICE_INSTR  + ","
                + COL_EXERCICE_VIDEO  + ","
                + COL_EXERCICE_IMAGE + ") VALUES (?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, exercice.getNom());
        statement.bindString(2, exercice.getCategorie());
        statement.bindString(3, exercice.getDescription());
        statement.bindString(4, exercice.getInstructions());
        statement.bindString(5, exercice.getUrlVideo());
        statement.bindString(6, exercice.getCheminImage());
        long newRowId = statement.executeInsert(); // return -1 if no row was added
        statement.close();
        db.close();
        return newRowId > -1;
    }

    public boolean updateOne(Exercice exercice) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + EXERCICE_TABLE + " SET "
                + COL_EXERCICE_NOM +   " = ?, "
                + COL_EXERCICE_CATEG + " = ?, "
                + COL_EXERCICE_DESC +  " = ?, "
                + COL_EXERCICE_INSTR + " = ?, "
                + COL_EXERCICE_VIDEO + " = ?, "
                + COL_EXERCICE_IMAGE + " = ? WHERE "
                + COL_EXERCICE_ID +    " = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, exercice.getNom());
        statement.bindString(2, exercice.getCategorie());
        statement.bindString(3, exercice.getDescription());
        statement.bindString(4, exercice.getInstructions());
        statement.bindString(5, exercice.getUrlVideo());
        statement.bindString(6, exercice.getCheminImage());
        statement.bindLong(7, exercice.getId());
        int numRowsAffected = statement.executeUpdateDelete(); // return 0 if no row was affected
        statement.close();
        db.close();
        return numRowsAffected > 0;
    }

    public boolean deleteOne(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + EXERCICE_TABLE + " WHERE " + COL_EXERCICE_ID + " = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindLong(1, id);
        int numRowsAffected = statement.executeUpdateDelete(); // return 0 if no row was affected
        statement.close();
        db.close();
        return numRowsAffected > 0;
    }

    /////////

}
