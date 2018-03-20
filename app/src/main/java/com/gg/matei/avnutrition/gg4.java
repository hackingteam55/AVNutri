package com.gg.matei.avnutrition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class gg4 extends AppCompatActivity implements View.OnClickListener {

    EditText alimente;
    Button save;
    Spinner spinnerTipulMesei;
    EditText cantitate;
    Button jurnalul_meu;

    DatabaseReference databaseAlimente;

    ListView lista_jurnal2;

    List<Alimente> jurnal2;

    private FirebaseAuth firebaseAuth;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gg4);

        databaseAlimente = FirebaseDatabase.getInstance().getReference("alimente");
        alimente = (EditText) findViewById(R.id.alimente);
        save = (Button) findViewById(R.id.save);
        spinnerTipulMesei = (Spinner) findViewById(R.id.spinnerTipulMesei);
        cantitate = (EditText) findViewById(R.id.cantitate);

        lista_jurnal2 = (ListView)findViewById(R.id.lista_jurnal2);

        jurnal2 = new ArrayList<Alimente>();

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, gg3.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        buttonLogout = (Button)findViewById(R.id.logout);

        buttonLogout.setOnClickListener(this);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlimente();

            }
        });

        jurnalul_meu = (Button)findViewById(R.id.jurnalul_meu);

        jurnalul_meu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jurnal = new Intent(getApplicationContext(),gg5.class);
                startActivity(jurnal);

            }
        });

    }


    @Override
    public void onClick(View view) {
        if (view == buttonLogout)
            firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, gg3.class));



    }

    private void addAlimente(){
        String name = alimente.getText().toString().trim();
        String fel = spinnerTipulMesei.getSelectedItem().toString().trim();
        String cant = cantitate.getText().toString().trim();

        if(!TextUtils.isEmpty(name)){

            String id = databaseAlimente.push().getKey();


            Alimente alimente = new Alimente(id, name, fel, cant);

            databaseAlimente.child(id).setValue(alimente);

            Toast.makeText(this, "Aliment adaugat", Toast.LENGTH_LONG).show();






        }else {
            Toast.makeText(this, "Introduceti un nume pentru aliment", Toast.LENGTH_LONG).show();

        }




    }



}




