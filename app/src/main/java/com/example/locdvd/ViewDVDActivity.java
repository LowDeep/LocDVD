package com.example.locdvd;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class ViewDVDActivity extends Activity {

    TextView txtTitreDVD;
    TextView txtAnneeDVD;
    TextView txtActeur1;
    TextView txtActeur2;
    TextView txtResumeFilm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //on appel le fichier xml ou y a les ressources
        setContentView(R.layout.activity_viewdvd);

        //onrecupere les textview a partir des id
        txtTitreDVD = (TextView) findViewById(R.id.titreDVD);
        txtAnneeDVD = (TextView) findViewById(R.id.anneeDVD);
        txtActeur1 = (TextView) findViewById(R.id.acteur1);
        txtActeur2 = (TextView) findViewById(R.id.acteur2);
        txtResumeFilm = (TextView) findViewById(R.id.resumeFilm);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        txtTitreDVD.setText(String.format(getString(R.string.titre_du_film),"Le petit nicolas"));
        txtAnneeDVD.setText(String.format(getString(R.string.annee_de_sortie),2014));
        txtActeur1.setText(String.format(getString(R.string.acteurs),"Acteur 1"));
        txtActeur2.setText(String.format(getString(R.string.acteurs),"Acteur 2"));
        String resume = " c'est la fin de l'année scolaire, le moment tant attendu des vances est arrivé"+
                        "lkengleknglkengjrnk elkngelknglkeg";
        txtResumeFilm.setText(String.format(getString(R.string.resume),resume));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }


}
