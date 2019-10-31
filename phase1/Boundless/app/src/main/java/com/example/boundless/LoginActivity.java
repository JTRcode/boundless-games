package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.boundless.users.UserAccount;
import com.example.boundless.users.UserAccountManager;

public class LoginActivity extends AppCompatActivity {

    /**
     * Manages user accounts and saves them
     */
    private UserAccountManager userManager;

    /**
     * The username and password input boxes
     */
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
        if(Session.getTheme()){
            getWindow().setBackgroundDrawableResource(R.drawable.halloween_log_in);
        }
        else{
            getWindow().setBackgroundDrawableResource(R.drawable.log_in);
        }
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
            //TODO: Send user to MenuActivity
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
    public void setBackground(){
       Session.setBackground(R.drawable.login);
    }

    public void setHalloweenLogin(){
        Session.setBackground(R.drawable.halloween_log_in);
    }*/
}