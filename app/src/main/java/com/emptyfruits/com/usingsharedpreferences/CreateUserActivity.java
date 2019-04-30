package com.emptyfruits.com.usingsharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.emptyfruits.com.usingsharedpreferences.databinding.ActivityMainBinding;

public class CreateUserActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    public static final String TAG = CreateUserActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mainBinding.username.getText().toString();
                String password = mainBinding.password.getText().toString();
                SharedPreferences preferences =
                        getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(getString(R.string.username_key), username);
                editor.putString(getString(R.string.password_key), password);
                editor.putBoolean(getString(R.string.logged_in_key), false);
                if (editor.commit()) {
                    Intent intent =
                            new Intent(CreateUserActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
