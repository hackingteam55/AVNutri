package com.gg.matei.avnutrition;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class gg2 extends AppCompatActivity implements View.OnClickListener, TextWatcher, CompoundButton.OnCheckedChangeListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private CheckBox savepassword;
    private static final String PREF_NAME="prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gg2);




        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),gg4.class ));
        }

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.button3);

        editTextEmail = (EditText)findViewById(R.id.emailregister);
        editTextPassword = (EditText)findViewById(R.id.passwordregister);

        textViewSignup = (TextView)findViewById(R.id.textalreadysign);

        buttonRegister.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        savepassword = (CheckBox)findViewById(R.id.savepassword);

        if(sharedPreferences.getBoolean(KEY_REMEMBER, false))
            savepassword.setChecked(true);
        else
            savepassword.setChecked(false);

        editTextEmail.setText(sharedPreferences.getString(KEY_USERNAME, ""));
        editTextPassword.setText(sharedPreferences.getString(KEY_PASS, ""));

        editTextEmail.addTextChangedListener(this);
        editTextPassword.addTextChangedListener(this);
        savepassword.setOnCheckedChangeListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        managePrefs();

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        managePrefs();

    }

    private void managePrefs(){
        if(savepassword.isChecked()){
            editor.putString(KEY_USERNAME, editTextEmail.getText().toString().trim());
            editor.putString(KEY_PASS, editTextPassword.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_USERNAME);//editor.putString(KEY_USERNAME, "");
            editor.apply();
        }
    }







    private void registerUser(){
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

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if successfull
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), gg4.class));
                        }else{
                            //display fail here
                            Toast.makeText(gg2.this, "Failed to register", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
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
            startActivity(new Intent(this, gg3.class
            ));
        }

    }

}