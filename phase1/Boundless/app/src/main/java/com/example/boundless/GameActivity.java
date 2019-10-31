package com.example.boundless;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.boundless.GPACatcherGame.GPACatcherGame;
import com.example.boundless.pixel_game.PixelGame;
import com.example.boundless.rotate_tile_game.RotateTileGame;

import java.util.Observable;
import java.util.Observer;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;

public class GameActivity extends Activity implements Observer {
    MediaPlayer player;

    /**
     * The panel playing the game
     */
    private Panel panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Statistics.start();
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
        if (game != null) {
            setContentView(R.layout.game_page);
            panel = findViewById(R.id.panel);
            panel.chooseGame(this, game);
            Log.d("GameActivity", "Changing to game: " + game);
        } else {
            Log.d("GameActivity", "An error occurred trying to get the game chosen.");
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
        Game currentGame = panel.getGame();
        currentGame.addObserver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(player != null){
            player.release();
            player = null;
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        moveToNextGame((Game) o);
    }

    /**
     * Changes the activity to the next game
     *
     * @param currentGame The current game being played
     */
    public void moveToNextGame(Game currentGame) {
        Statistics.end();
        Intent intent = new Intent(this, GameActivity.class);
        if (currentGame instanceof PixelGame) {
            intent.putExtra("GAME", GamesEnum.ROTATETILE);
        } else if (currentGame instanceof RotateTileGame) {
            intent.putExtra("GAME", GamesEnum.GPACATCHER);
        } else if (currentGame instanceof GPACatcherGame) {
            intent = new Intent(this, MenuActivity.class);
        }
        startActivity(intent);
    }

    /**
     * Shows the text on screen that the game is finished.
     */
    private void showGameOverText(){
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.bottomToBottom = params.topToTop =
                params.startToStart = params.endToEnd = R.id.ConstraintLayout;
        params.verticalBias = (float) 0.5;
        TextView textView = new TextView(this);
        textView.setText(R.string.game_over);
        textView.setLayoutParams(params);
        ((ConstraintLayout) findViewById(R.id.ConstraintLayout)).addView(textView);
    }

    /**
     * Override hardware back button to include stopping time for statistics calculation
     */
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Statistics.end();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
