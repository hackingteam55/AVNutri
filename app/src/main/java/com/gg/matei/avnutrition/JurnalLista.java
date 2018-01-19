package com.gg.matei.avnutrition;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by matei.oltean on 19/01/2018.
 */

public class JurnalLista extends ArrayAdapter<Alimente> {
    private Activity context;
    private List<Alimente> jurnal2;

    public JurnalLista(Activity context, List<Alimente> jurnal2) {
        super(context, R.layout.lista_jurnal, jurnal2);

        this.context = context;
        this.jurnal2 = jurnal2;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewIteam = inflater.inflate(R.layout.lista_jurnal, null, true);

        TextView aliment_jurnal = (TextView) listViewIteam.findViewById(R.id.aliment_tip);
        TextView aliment_tip = (TextView) listViewIteam.findViewById(R.id.aliment_jurnal);
        TextView aliment_cantitate = (TextView) listViewIteam.findViewById(R.id.aliment_cantitate);

        Alimente alimente = jurnal2.get(position);

        aliment_jurnal.setText(alimente.getAlimentId4());
        aliment_tip.setText(alimente.getAlimentId2());
        aliment_cantitate.setText(alimente.getAlimentId3());

        return listViewIteam;
    }
}


