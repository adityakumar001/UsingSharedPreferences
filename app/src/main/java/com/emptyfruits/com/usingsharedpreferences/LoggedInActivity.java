package com.emptyfruits.com.usingsharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.emptyfruits.com.usingsharedpreferences.databinding.ActivityLoggedInBinding;

public class LoggedInActivity extends AppCompatActivity {

    ActivityLoggedInBinding loggedInBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loggedInBinding = DataBindingUtil.setContentView(this, R.layout.activity_logged_in);
        final SharedPreferences sharedPreferences =
                getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
        Intent intent;
        if (sharedPreferences.getBoolean(getString(R.string.logged_in_key), false)) {
            String username = sharedPreferences.getString(getString(R.string.username_key),
                    "username");
            loggedInBinding.loggedInUsername.setText(username);
        } else {
            if (!sharedPreferences.contains(getString(R.string.username_key))) {
                intent = (new Intent(this, CreateUserActivity.class));
                finish();
            } else {
                intent = (new Intent(LoggedInActivity.this, LoginActivity.class));
                finish();
            }
            startActivity(intent);
        }
        loggedInBinding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(getString(R.string.logged_in_key), false);
                if (editor.commit()) {
                    startActivity(new Intent(LoggedInActivity.this, LoginActivity.class));
                    //finish() method is called to destroy current activity so that the user won't
                    // be able to go to this activity again by pressing the back button.
                    finish();
                }
            }
        });
        loggedInBinding.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                if (editor.commit()) {
                    startActivity(new Intent(LoggedInActivity.this, CreateUserActivity.class));
                    finish();
                }

            }
        });
    }
}
