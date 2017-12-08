package com.gg.matei.avnutrition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class gg4 extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gg4);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, gg3.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        buttonLogout = (Button)findViewById(R.id.logout);

        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == buttonLogout){

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, gg3.class));
        }

    }
}
