package com.gg.matei.avnutrition;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class gg3 extends AppCompatActivity implements View.OnClickListener {

    private Button login_button;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewLogin;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gg3);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),gg4.class ));
        }

        editTextEmail = (EditText)findViewById(R.id.email_login);
        editTextPassword = (EditText)findViewById(R.id.password_login);
        login_button = (Button)findViewById(R.id.login_button);
        textViewLogin = (TextView)findViewById(R.id.sing_up_login);


        progressDialog = new ProgressDialog(this);

        login_button.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
    }

    private void userLogin(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
            //stopping the function
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show();
            //stopping the function
            return;
        }
        //if validations are ok
        //we will register the user

        progressDialog.setMessage("Registering, please wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                          startActivity(new Intent(getApplicationContext(),gg4.class ));
                        }

                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view == login_button) {
            userLogin();
        }
        if(view == textViewLogin){
            finish();
            startActivity(new Intent(this,gg2.class));
        }
    }
}
