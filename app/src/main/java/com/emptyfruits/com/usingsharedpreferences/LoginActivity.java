package com.emptyfruits.com.usingsharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.emptyfruits.com.usingsharedpreferences.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = loginBinding.loginUsername.getText().toString();
                String password = loginBinding.loginPassword.getText().toString();
                SharedPreferences preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                Log.d(TAG, "onClick: " + preferences.contains(getString(R.string.username_key)));
                Log.d(TAG, "onClick: " + username + password);
                Log.d(TAG, "onClick: " + preferences.getString(getString(R.string.username_key)
                        , "user"));
                if (preferences.getString(getString(R.string.username_key), "username")
                        .equals(username)
                        && preferences.getString(getString(R.string.password_key)
                        , "password").equals(password)) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(getString(R.string.logged_in_key), true);
                    if (editor.commit()) {
                        startActivity(new Intent(LoginActivity.this, LoggedInActivity.class));
                        finish();
                    }
                }

            }
        });
    }
}
