package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.boundless.games.GamesEnum;
import com.example.boundless.utilities.Session;

public class LevelActivity extends AppCompatActivity {
    GamesEnum game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        game = (GamesEnum) getIntent().getSerializableExtra(IntentExtras.gameEnum);

        createButtons();
        if (Session.getTheme(this)) {
            //getWindow().setBackgroundDrawableResource(R.drawable.hallo_menu);
        } else {
            getWindow().setBackgroundDrawableResource(R.drawable.level);
        }
    }

    private void createButtons(){

    }

    /**
     * Start the game with different levels
     *
     * @param view the button clicked
     */
    public void startLevel(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(IntentExtras.gameEnum, game);
        switch (view.getId()) {
            case R.id.level_1:
                intent.putExtra(IntentExtras.levelNumber, 0);
                break;
            case R.id.level_2:
                intent.putExtra(IntentExtras.levelNumber, 1);
                break;
            case R.id.level_3:
                intent.putExtra(IntentExtras.levelNumber, 2);
                break;
            case R.id.level_4:
                intent.putExtra(IntentExtras.levelNumber, 3);
                break;
            default:
                break;
        }
        startActivity(intent);
    }

    /**
     * Override hardware back button to go to the menu activity
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
