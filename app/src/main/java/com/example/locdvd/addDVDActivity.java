package com.example.locdvd;


import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class addDVDActivity extends Activity {

    String titre_du_film;
    String annee_de_sortie;
    EditText editTitreFilm;
    EditText editAnnee;
    EditText editResume;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //affectation du fichier layout
        setContentView(R.layout.activity_adddvd);

        editTitreFilm = (EditText)findViewById(R.id.addDVD_titre);
        editAnnee = (EditText)findViewById(R.id.addDVD_annee);
        editResume = (EditText)findViewById(R.id.addDVD_resume);



    }
}
