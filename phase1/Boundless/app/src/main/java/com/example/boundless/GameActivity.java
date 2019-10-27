package com.example.boundless;

import android.app.Activity;
import android.content.Intent;
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

        //getWindow().setBackgroundDrawableResource(R.drawable.backgroundone);

        /**imageView.setImageResource(R.drawable.backgroundtwo);
         setContentView(imageView);*/
        player = MediaPlayer.create(this, R.raw.novoamorweather);
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
            panel.chooseGame(game);

            System.out.println("Changing to game: " + game);
        } else {
            Log.d("GameActivity", "An error occurred trying to get the game chosen.");
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }

    }

    protected void showToast(String message) {
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.release();
        finish();
    }
}
