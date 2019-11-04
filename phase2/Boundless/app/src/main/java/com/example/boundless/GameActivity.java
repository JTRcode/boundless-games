package com.example.boundless;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.boundless.games.GamesEnum;
import com.example.boundless.games.Game;
import com.example.boundless.utilities.Session;

import java.util.Observable;
import java.util.Observer;

public class GameActivity extends Activity implements Observer {
    MediaPlayer player;

    /**
     * The panel playing the game
     */
    private Panel panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setupGame();
    }

    /**
     * Sets up the game
     */
    private void setupGame() {
        setCurrentGame();
        findViewById(R.id.ConstraintLayout).setBackgroundResource(Session.getBackground());
        player = MediaPlayer.create(this, Session.getMusic());
        player.setLooping(true);
        player.start();
    }

    /**
     * Sets up the views for the current game.
     */
    private void setCurrentGame() {
        GamesEnum game = (GamesEnum) getIntent().getSerializableExtra("GAME");
        int level = 0;
        if(game == GamesEnum.PIXELS){
            level = (int) getIntent().getSerializableExtra("currentLevel");
        }
        //int level = (int) getIntent().getSerializableExtra("currentLevel");
        if (game != null) {
            setContentView(R.layout.game_page);
            panel = findViewById(R.id.panel);
            panel.chooseGame(game, level);
            Log.d("GameActivity", "Changing to game: " + game);
        } else {
            Log.e("GameActivity", "An error occurred trying to get the game chosen.");
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
        Game currentGame = panel.getGame();
        currentGame.addObserver(this);
        currentGame.showInstructions();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.release();
            player = null;
        }
    }

    /**
     * Show an overlay with info from the game
     *
     * @param text The text to show on the overlay
     */
    private void showOverlay(String text, final boolean gameIsOver) {
        panel.pause();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text).setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (gameIsOver) onBackPressed();
                        else panel.resume();
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onBackPressed();
                    }
                });
        builder.create().show();
    }

    @Override
    public void update(Observable observable, final Object o) {
        try {
            runOnUiThread(new Runnable() {
                public void run() {
                    showOverlay((String) o, panel.isGameOver());
                }
            });
        } catch (ClassCastException e) {
            Log.e("GameActivity", "The observer needs a string to show an alert!");
        }
    }

    /**
     * Override hardware back button to go to main activity
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
