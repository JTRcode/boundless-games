package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.boundless.games.GamesEnum;
import com.example.boundless.utilities.HandleCustomization;

public class LevelActivity extends AppCompatActivity {
    GamesEnum game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        game = (GamesEnum) getIntent().getSerializableExtra(IntentExtras.gameEnum);

        createButtons();
        HandleCustomization.setActivityBackground(this, getWindow());
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
        int num = Integer.parseInt((String) ((Button)view).getText());
        intent.putExtra(IntentExtras.levelNumber, num);
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
