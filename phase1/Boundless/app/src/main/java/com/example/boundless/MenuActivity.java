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
    /**
     * The current user, null if not logged in
     */
    UserAccount user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    /**
     * Start the chosen game.
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

}
