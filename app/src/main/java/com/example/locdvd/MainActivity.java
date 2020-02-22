package com.example.locdvd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.locdvd.Classes.DVD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.main_List);

        //fichiers
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.locDVD.prefs",Context.MODE_PRIVATE);
        if(!sharedPreferences.getBoolean("embeddedDataInserted",false)){
            readEmbeddedData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<DVD> dvdList = DVD.getDVDList(this);
        DVDAdapter dvdAdapter = new DVDAdapter(this,dvdList);
        list.setAdapter(dvdAdapter);
    }

    //methode pour lire les lignes du fichier assets.data.txt et les mettre dans des DVD
    //les lignes du fichier : les donnees separe par | et les acteurs par ,
    private void readEmbeddedData(){
        InputStreamReader reader = null;
        InputStream file = null;
        BufferedReader bufferedReader = null;

        try{
            file = getAssets().open("data.txt");
            reader = new InputStreamReader(file);
            bufferedReader = new BufferedReader(reader);
            String line = null;

            while( ( line = bufferedReader.readLine() ) != null){
                String[] data = line.split("\\|");
                if(data != null && data.length == 4 ){
                    DVD dvd = new DVD();
                    dvd.setTitre(data[0]);
                    dvd.setAnnee(Integer.decode(data[1]));
                    dvd.setActeur(data[2].split(","));
                    dvd.setResume(data[3]);

                    dvd.insert(this); ///on insere le dvd dans ce contexte la(cette activite)
                    //CE QUON VEUT FAIRE DU DVD APRES L'AVOIR LU  : L'INSERER DANS LA BDD
                }
            }
        }   catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bufferedReader != null)
            {
                try{
                    bufferedReader.close();
                    reader.close();
                    SharedPreferences sharedPreferences = getSharedPreferences("com.exemple.locDVD.prefs",Context.MODE_PRIVATE);//??
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("enbeddedDataInsered",true);
                    editor.commit();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }






}
