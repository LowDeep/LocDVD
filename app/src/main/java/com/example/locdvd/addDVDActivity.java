package com.example.locdvd;


import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class addDVDActivity extends Activity {

    LinearLayout addActeursLayout;
    EditText editTitreFilm;
    EditText editAnnee;
    EditText editResume;
    Button btnOk;
    Button btnAddActeur;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //affectation du fichier de layout
        setContentView(R.layout.activity_adddvd);

        //Obtention des références sur les composants :depuis fichier activity_adddvd.xml
        editTitreFilm = (EditText)findViewById(R.id.addDVD_titre);
        editAnnee = (EditText)findViewById(R.id.addDVD_annee);
        editResume = (EditText)findViewById(R.id.addDVD_resume);

        btnAddActeur = (Button)findViewById(R.id.addDVD_addActeur);
        btnOk = (Button)findViewById(R.id.addDVD_ok);
        addActeursLayout = (LinearLayout)findViewById(R.id.addDVD_addActeurLayout);

        //Déclaration du CLIQUE SUR LE BOUTON +
        btnAddActeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addActeur(null);
            }
        });
        //est ce que c'est une recréation suite a une rotation de l'écran ?
        if(savedInstanceState != null)
        {
            String [] acteurs = savedInstanceState.getStringArray("acteurs");
                for(String s : acteurs)
                {
                    addActeur(s);
                }

        }else{
            //Aucun acteur saisi, on affiche un composant edit Text vide
        addActeur(null);
        }


    }

    //pr sauvegarder les acteurs fils
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        String[] acteurs = new String[addActeursLayout.getChildCount()];
        for(int i=0; i<addActeursLayout.getChildCount();i++)
        {
            View child = addActeursLayout.getChildAt(i);
            if(child instanceof  EditText)
            {
                acteurs[i] = ((EditText)child).getText().toString();
            }
        }
        //METHODE NE MARCHE PAS!!! faut mettre dans un fichier ou bdd
       // savedInstanceState.putStringArray("acteurs",acteurs);
       // super.onSaveInstanceState(savedInstantState);
    }

    private void addActeur(String content){
        EditText editNewActeur = new EditText(this);
        //pour gerer l'entrée utilisateur vu que c'est gerer dynamiquement on met ici et non pas dans le fichier xml
        editNewActeur.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME|InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        if(content != null)
        {
            editNewActeur.setText(content);
        }
        addActeursLayout.addView(editNewActeur);
    }
}
