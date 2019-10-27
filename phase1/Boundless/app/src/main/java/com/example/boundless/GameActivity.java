package com.example.boundless;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends Activity {
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCurrentGame();
        findViewById(R.id.ConstraintLayout).setBackgroundResource(Session.getBackground());
        player = MediaPlayer.create(this,Session.getMusic());
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
            Panel panel = findViewById(R.id.panel);
            panel.chooseGame(this, game);

            System.out.println("Changing to game: " + game);
        } else {
            Log.d("GameActivity", "An error occurred trying to get the game chosen.");
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onPause(){
       super.onPause();
       player.release();
       finish();
    }
}
