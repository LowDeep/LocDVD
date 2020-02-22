package com.example.locdvd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.locdvd.Classes.DVD;

import java.util.List;

public class DVDAdapter extends ArrayAdapter<DVD> {

    Context context;

    public DVDAdapter(Context context , List<DVD> listDVD){
        super(context,-1, listDVD);
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listitem_dvd,parent);
        }else{
            view = convertView;
        }
        DVD dvd = getItem(position);

        TextView titre = (TextView)view.findViewById(R.id.listItemDVD_titre);
        TextView annee = (TextView)view.findViewById(R.id.listItemDVD_annee);
        TextView resume = (TextView)view.findViewById(R.id.listItemDVD_resume);

        titre.setText(dvd.getTitre());
        annee.setText(String.valueOf(dvd.getAnnee()));
        resume.setText(dvd.getResume());

        return view;
    }
}
