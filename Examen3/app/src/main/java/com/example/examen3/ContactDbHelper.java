package com.example.examen3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bdcontacts";
    private static final int DATABASE_VERSION = 1;
    private final String CONTACT_TABLE = "contacts";
    private final String COL_CONTACT_ID = "_id";
    private final String COL_CONTACT_PRENOM = "prenom";
    private final String COL_CONTACT_NOM = "nom";
    private final String COL_CONTACT_TELEPHONE = "telephone";

    private Context context;

    public ContactDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ CONTACT_TABLE + " ("
                + COL_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_CONTACT_PRENOM + " TEXT, "
                + COL_CONTACT_NOM + " TEXT, "
                + COL_CONTACT_TELEPHONE + " TEXT)";
        db.execSQL(sql);
        if (isDatabaseEmpty(db)) {
            try {
                preInsertContact(db, prenom1, nom1, telephone1);
                preInsertContact(db, prenom2, nom2, telephone2);
                preInsertContact(db, prenom3, nom3, telephone3);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isDatabaseEmpty(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + CONTACT_TABLE, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count == 0;
    }

    private void preInsertContact(SQLiteDatabase db, String nom, String prenom, String telephone) throws IOException {
        ContentValues values = new ContentValues();
        values.put("nom", nom);
        values.put("prenom", prenom);
        values.put("telephone", telephone);
        db.insert(CONTACT_TABLE, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTACT_TABLE);
        onCreate(db);
    }

    ///////// CRUD without U

    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + CONTACT_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) { // we move the cursor over the first row of its attached result set
            do {
                long id = cursor.getLong(0);
                String nom = cursor.getString(1);
                String prenom = cursor.getString(2);
                String telephone = cursor.getString(3);
                //
                Contact contact = new Contact(id, nom, prenom, telephone);
                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contacts;
    }

    public Contact getOneByTelephone(String telephone) {
        Contact contact = new Contact();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + CONTACT_TABLE + " WHERE " + COL_CONTACT_TELEPHONE + " = '" + telephone + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) { // we move the cursor over the first row of its attached result set
            long id = cursor.getLong(0);
            String nom = cursor.getString(1);
            String prenom = cursor.getString(2);
            //
            contact.setId(id);
            contact.setNom(nom);
            contact.setPrenom(prenom);
            contact.setTelephone(telephone);
        }
        cursor.close();
        db.close();
        return contact;
    }

    public Contact getOne(long id) {
        Contact contact = new Contact();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + CONTACT_TABLE + " WHERE " + COL_CONTACT_ID + " = '" + id + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) { // we move the cursor over the first row of its attached result set
            do {
                String nom = cursor.getString(1);
                String prenom = cursor.getString(2);
                String telephone = cursor.getString(3);
                //
                contact.setId(id);
                contact.setNom(nom);
                contact.setPrenom(prenom);
                contact.setTelephone(telephone);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contact;
    }

    public boolean addOne(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO " + CONTACT_TABLE + " ("
                + COL_CONTACT_NOM        + ","
                + COL_CONTACT_PRENOM     + ","
                + COL_CONTACT_TELEPHONE  + ") VALUES (?,?,?)";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, contact.getNom());
        statement.bindString(2, contact.getPrenom());
        statement.bindString(3, contact.getTelephone());
        long newRowId = statement.executeInsert(); // return -1 if no row was added
        statement.close();
        db.close();
        return newRowId > -1;
    }

    public boolean deleteOne(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + CONTACT_TABLE + " WHERE " + COL_CONTACT_ID + " = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindLong(1, id);
        int numRowsAffected = statement.executeUpdateDelete(); // return 0 if no row was affected
        statement.close();
        db.close();
        return numRowsAffected > 0;
    }

    ///////// PRE-INSERTED DATA

    String nom1 = "Normandin";
    String prenom1 = "David";
    String telephone1 = "14385236969";
    //
    String nom2 = "Moreau";
    String prenom2 = "Richard";
    String telephone2 = "14385236969";
    //
    String nom3 = "LeMagnifique";
    String prenom3 = "Kiwi";
    String telephone3 = "14385236969";
    //
}


