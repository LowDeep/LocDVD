package com.example.locdvd.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.locdvd.DB.LocalSQLiteOpenHelper;

import java.util.ArrayList;

public class DVD {

    private long id;
    private String titre;
    private int annee;
    private String[] acteurs;
    private String resume;

    //Contructeur1
    private DVD(Cursor cursor)
    {
        id = cursor.getLong(cursor.getColumnIndex("id"));
        titre = cursor.getString(cursor.getColumnIndex("titre"));
        annee = cursor.getInt(cursor.getColumnIndex("annee"));
        acteurs = cursor.getString(cursor.getColumnIndex("acteurs")).split(";");
        resume = cursor.getString(cursor.getColumnIndex("resume"));
    }

    //Constructeur 2
    public DVD(){

    }

    //GETTERS - SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String[] getActeur() {
        return acteurs;
    }

    public void setActeur(String[] acteur) {
        this.acteurs = acteur;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }


    //METHODE DE MANIPULATION DE DONNEEs
    //obtenir liste dvd dans la db
    public static ArrayList<DVD> getDVDList(Context context)
    {
        ArrayList<DVD> listeDVD = new ArrayList<>();
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true, "DVD", new String[]{"id","titre","annee","acteurs", "resume"},null,null,null,null,"titre",null);

        while (cursor.moveToNext())
        {
            listeDVD.add(new DVD(cursor));
        }
        cursor.close();
        db.close();

        return listeDVD;
    }

    //obtenir dvd depuis son id
    public static DVD getDVD(Context context , long id)
    {
        DVD dvd = null;
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper((context));

        SQLiteDatabase db = helper.getReadableDatabase();
        String where = "id = " +String.valueOf(id);
        Cursor cursor = db.query(true,"DVD", new String[] {"id","titre","annee","acteurs","resume"},where,null,null,null,"titre",null);

        if(cursor.moveToFirst())
        {
            dvd = new DVD(cursor);
        }

        cursor.close();
        db.close();

        return dvd;
    }

    //inserer une nouvelle instance DVD
    public void insert(Context context){

        ContentValues values = new ContentValues();
        values.put("titre",this.titre);
        values.put("annee",this.annee);
        if(this.acteurs != null)
        {
            String listeActeurs = new String();
            for(int i=0; i<this.acteurs.length; i++){
                listeActeurs += this.acteurs[i];
                if(i < this.acteurs.length -1)
                {
                    listeActeurs += ";";
                }
            }
            values.put("acteurs", listeActeurs);
        }
        values.put("resume",this.resume);


        //insertion
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);

        SQLiteDatabase db = helper.getWritableDatabase();
        //inserer + recup l'id du dvd
        this.id= db.insert("DVD",null,values);
        db.close();

    }

    //mettre a jour un enregistrement
    public void update(Context context)
    {
        ContentValues values = new ContentValues();
        values.put("titre", this.titre);
        values.put("annee",this.annee);
        if(this.acteurs != null)
        {
            String listeActeurs = new String();
            for(int i=0; i<this.acteurs.length; i++){
                listeActeurs += this.acteurs[i];
                if(i < this.acteurs.length -1)
                {
                    listeActeurs += ";";
                }
            }
            values.put("acteurs", listeActeurs);
        }
        values.put("resume",this.resume);
        String whereClause = "id = "+ String.valueOf(this.id);

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);

        SQLiteDatabase db = helper.getWritableDatabase();

        db.update("DVD",values,whereClause,null);
        db.close();
    }

    //supprimer l'instance
    public void delete(Context context){

        String whereClause = "id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = String.valueOf(this.id);

        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);

        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete("DVD", whereClause,whereArgs);
        db.close();
    }




}
