package com.example.roba.skinbeauty;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText editTextUsername;
    private TextInputEditText editTextFirstName;
    private TextInputEditText editTextLastName;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPhone;
    private TextInputEditText editTextPassword;
    private TextInputEditText editTextRePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");


        editTextUsername = findViewById(R.id.editText_username);
        editTextFirstName = findViewById(R.id.editText_first_name);
        editTextLastName = findViewById(R.id.editText_last_name);
        editTextEmail = findViewById(R.id.editText_email);
        editTextPhone = findViewById(R.id.editText_phone);
        editTextPassword = findViewById(R.id.editText_password);
        editTextRePassword = findViewById(R.id.editText_repassword);
    }

    public void register(View view) {

        String username = editTextUsername.getText().toString();
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();
        String password = editTextPassword.getText().toString();
    }

    public void registerByFacebook(View view) {
    }

    public void registerByGoogle(View view) {
    }
}
