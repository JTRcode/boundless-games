package com.example.boundless.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.boundless.R;
import com.example.boundless.users.UserAccount;
import com.example.boundless.users.UserAccountManager;
import com.example.boundless.utilities.HandleCustomization;
import com.example.boundless.utilities.Session;

/**
 * The login screen
 */
public class LoginActivity extends AppCompatActivity {

    private UserAccountManager userManager;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Session.setupSession(this);
        userManager = new UserAccountManager(this);
        if (userManager.notSignedIn()) {
            setContentView(R.layout.login_page);
            username = findViewById(R.id.username);
            password = findViewById(R.id.password);
        } else {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
        HandleCustomization.setActivityBackground(this, getWindow());
    }

    /**
     * Signs the user into their account
     *
     * @param view The login button
     */
    public void signIn(View view) {
        String name = username.getText().toString();
        String pass = password.getText().toString();
        UserAccount user = userManager.signIn(name, pass);
        if (user != null) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Sign up a new user.
     *
     * @param view The signup button
     */
    public void signUp(View view) {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        if (userManager.signUp(user, pass)) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Override hardware back button to exit the app
     */
    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }
}