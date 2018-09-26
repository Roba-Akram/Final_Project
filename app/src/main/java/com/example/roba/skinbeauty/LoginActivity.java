package com.example.roba.skinbeauty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {



    private TextInputLayout layoutUsername;
    private TextInputLayout layoutPassword;
    private TextInputEditText editTextUsername;
    private TextInputEditText editTextPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");

        layoutUsername = findViewById(R.id.layout_username);
        layoutPassword = findViewById(R.id.layout_password);
        editTextUsername = findViewById(R.id.editText_username);
        editTextPassword = findViewById(R.id.editText_password);

        if (getIntent().hasExtra("username")) {
            String username = getIntent().getStringExtra("username");
            editTextUsername.setText(username);
        }

    }

    public void login(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if (username.isEmpty()) {
            layoutUsername.setError("Enter username");
            layoutPassword.setError(null);

        } else if (password.isEmpty()) {
            layoutUsername.setError(null);
            layoutPassword.setError("Enter password");

        } else {

        }

    }

    public void register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


    public void loginByFacebook(View view) {
    }

    public void loginByGoogle(View view) {
    }
}
