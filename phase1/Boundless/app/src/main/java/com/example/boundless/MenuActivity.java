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
public class MenuActivity extends AppCompatActivity {
    //TODO: show stats on the menu screen
    //TODO: create customization options (will need a new class)
    //TODO: get current user from login activity
    /**
     * The current user, null if not logged in
     */
    UserAccount user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        //setBackground();
    }

    /**
     * Start the chosen game.
     *
     * @param view The chosen button that was clicked
     */
    public void startGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);

        switch (view.getId()) {
            case R.id.GPACatcherGame:
                intent.putExtra("GAME", GamesEnum.GPACATCHER);
                break;
            case R.id.RotateTile:
                intent.putExtra("GAME", GamesEnum.ROTATETILE);
                break;
            case R.id.PixelGame:
                intent.putExtra("GAME", GamesEnum.PIXELS);
                break;
            default:
                break;
        }
        startActivity(intent);
    }

    public void signOut(View v) {
        Session.clearUser();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void goCustomization(View v){
        Intent intent = new Intent(this, CustomizationActivity.class);
        startActivity(intent);
    }
    /**
    public void setBackground(){
        Session.setBackground(R.drawable.menu);
    }

    public void setHalloweenMenu(){
        Session.setBackground(R.drawable.halloween_main_menu);
    }*/

    public void goStatistics(View v){
        Intent intent = new Intent(this, StatisticsActivity.class);
        Statistics.end();
        startActivity(intent);
    }

}
