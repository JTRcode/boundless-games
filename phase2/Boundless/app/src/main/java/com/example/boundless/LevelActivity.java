package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.boundless.games.GamesEnum;

public class LevelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
    }

    /**
     * Start the game with different levels
     * @param view the button clicked
     */

    public void startPixelGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("GAME",GamesEnum.PIXELS);
        switch (view.getId()) {
            case R.id.level_1:
                intent.putExtra("currentLevel", 0);
                break;
            case R.id.level_2:
                intent.putExtra("currentLevel", 1);
                break;
            case R.id.level_3:
                intent.putExtra("currentLevel", 2);
                break;
            case R.id.level_4:
                intent.putExtra("currentLevel", 3);
            default:
                break;
        }
        startActivity(intent);
    }
}
