package com.gg.matei.avnutrition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class gg5 extends AppCompatActivity {

    DatabaseReference databaseAlimente;

    ListView lista_jurnal2;

    List<Alimente> jurnal2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gg5);


        lista_jurnal2 = (ListView) findViewById(R.id.lista_jurnal2);

        jurnal2 = new ArrayList<Alimente>();


    }
    @Override
    protected void onStart() {
        super.onStart();

        databaseAlimente.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                jurnal2.clear();

                for (DataSnapshot alimenteSnapshot : dataSnapshot.getChildren()){
                    Alimente alimente = alimenteSnapshot.getValue(Alimente.class);

                    jurnal2.add(alimente);
                }

                JurnalLista adapter = new JurnalLista(gg5.this, jurnal2);
                lista_jurnal2.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}

