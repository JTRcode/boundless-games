package com.example.boundless;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
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
        //getWindow().setBackgroundDrawableResource(R.drawable.backgroundone);

        /**imageView.setImageResource(R.drawable.backgroundtwo);
        setContentView(imageView);*/
        player = MediaPlayer.create(this,R.raw.novoamorweather);
        player.setLooping(true);
        player.start();

        GamesEnum game = (GamesEnum) getIntent().getSerializableExtra("GAME");
        setContentView(new Panel(this, game));
        System.out.println("Name: "+game);
    }
    protected void showToast(String message){
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }
    @Override
    protected void onPause(){
       super.onPause();
       player.release();
       finish();
    }
}
