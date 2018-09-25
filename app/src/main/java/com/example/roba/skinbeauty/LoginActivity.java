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

            layoutUsername.setError(null);
            layoutPassword.setError(null);

            if (Utility.isOnline(this)) {


                JSONObject jsonObject = new JSONObject();

                try {

                    jsonObject.put("client_secret", "HnHO5kq6qv39uDnA6FOO5MwNLqkPzzymh78OS06q");
                    jsonObject.put("client_id", 2);
                    jsonObject.put("grant_type", "password");
                    jsonObject.put("username", username);
                    jsonObject.put("password", password);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();


                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                        Api.LOGIN_URL,
                        jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, "onResponse: " + response.toString());
                                progressDialog.dismiss();

                                try {

                                    String tokenType = response.getString("token_type");
                                    String accessToken = response.getString("access_token");

                                    Log.d(TAG, "onResponse: " + accessToken);

                                    // Store in shared preferences
                                    SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("logged_in", true);
                                    editor.putString("token_type", tokenType);
                                    editor.putString("access_token", accessToken);
                                    editor.apply();

                                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "onErrorResponse: " + error.toString());
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, R.string.connection_failed, Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }

                };


                AppController.getInstance(this).addToRequestQueue(request);

            } else {
                Toast.makeText(this, R.string.no_connection, Toast.LENGTH_LONG).show();
            }

        }

    }

    public void register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


}
