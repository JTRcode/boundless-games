package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private UserAccountManager userManager;

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }
        */
        userManager = new UserAccountManager();
        if (notSignedIn()) {
            setContentView(R.layout.login_page);
            username = findViewById(R.id.username);
            password = findViewById(R.id.password);
        }
        else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private boolean notSignedIn() {
        //TODO: Nyah figure out if user is signed in
        return true;
    }

    public void signIn(View view) {
        if (userManager.signIn(username.getText().toString(), password.getText().toString())) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }
    public void signUp(View view) {
        //TODO: check if user already exists in userManager
        userManager.signUp(username.getText().toString(), password.getText().toString());
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}