package com.gg.matei.avnutrition;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class gg2 extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gg2);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.button3);

        editTextEmail = (EditText)findViewById(R.id.emailregister);
        editTextPassword = (EditText)findViewById(R.id.passwordregister);

        textViewSignup = (TextView)findViewById(R.id.textalreadysign);

        buttonRegister.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);

    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            //stopping the function
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            //stopping the function
            return;
        }
        //if validations are ok
        //we will register the user

        progressDialog.setMessage("Registering, please wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //user is successfully registered
                        //start activity profile here
                        Toast.makeText(gg2.this, "Registered Sucessfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(gg2.this, "Failed to Register :(", Toast.LENGTH_SHORT).show();
                    }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == buttonRegister){
            registerUser();

        }
        if(view == textViewSignup){
            //will open login activity here
        }

    }
}
