package com.example.boundless;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The main menu, controls log in and starting games
 */
public class Menu extends AppCompatActivity {
    /**
     * The current user, null if not logged in
     */
    UserAccount user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void startGame(View v){
        Intent intent = new Intent(this, GameActivity.class);
        /*
        switch (v.getId()){
            case R.id.GPACatcherGame:
                intent.putExtra("GAMENUM", 1);
                break;
            case R.id.RotateTile:
                intent.putExtra("GAMENUM", 2);
                break;
            case R.id.PixelGame:
                intent.putExtra("GAMENUM", 3);
                break;
            default:
                break;
        }*/
        Button button = (Button)findViewById(v.getId());
        intent.putExtra("GAMENAME",button.getText());
        startActivity(intent);
    }

    /**
     * Manages sign in and sign up
     */
    UserAccountManager accountManager;

    /**
     * Manages game starting and resuming
     */
    GameDeployer gameDeployer;

    /**
     * Starts or resumes a game
     */
    public void playGame(boolean resume){
        //TODO
    }

    /**
     * Signs a user in given credentials
     *
     * @return If the sign in is successful
     */
    public boolean signIn(String name){
        //TODO
        return false;
    }

    /**
     * Signs a user up given credentials
     * @param name the username of the new account.
     */
    public void signUp(String name){
        //TODO
    }

}
