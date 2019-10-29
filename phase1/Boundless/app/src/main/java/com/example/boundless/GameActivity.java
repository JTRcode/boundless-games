package com.example.boundless;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

public class GameActivity extends Activity {
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
            addGameOverButton(game);

            System.out.println("Changing to game: " + game);
        } else {
            Log.d("GameActivity", "An error occurred trying to get the game chosen.");
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Adds the game over button
     *
     * @param game The current game being played
     */
    private void addGameOverButton(GamesEnum game) {
        if (game != GamesEnum.PIXELS && game != GamesEnum.ROTATETILE) return;

        Button gameOver = new Button(this);
        gameOver.setText(R.string.game_over_button);
        gameOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panel.getGame().gameOver();
            }
        });
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        params.bottomToBottom = params.topToTop =
                params.startToStart = params.endToEnd = R.id.ConstraintLayout;
        params.verticalBias = (float) 0.82;
        gameOver.setLayoutParams(params);

        ((ConstraintLayout)findViewById(R.id.ConstraintLayout)).addView(gameOver);
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.release();
        finish();
    }

    /**
    * Override hardware back button to include stopping time for statistics calculation
    */
    @Override
    public void onBackPressed(){
        Statistics.end();
        super.onBackPressed();
    }
}
