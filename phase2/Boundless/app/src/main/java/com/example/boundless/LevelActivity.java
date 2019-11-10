package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.boundless.games.BusinessContext;
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

    private void createButtons() {
        GridLayout grid = findViewById(R.id.gridLayout);
        final Typeface font = ResourcesCompat.getFont(this, R.font.baloo_da);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLevel(view);
            }
        };
        for (int i = 0; i < BusinessContext.getNumOfLevels(game); i++) {
            Button newButton = new Button(this);
            newButton.setText(String.valueOf(i + 1));
            newButton.setTypeface(font);
            newButton.setBackgroundResource(R.drawable.level_button);
            newButton.setOnClickListener(listener);
            newButton.setPaddingRelative(0, 0, 10, 0);
            newButton.setTextColor(Color.WHITE);
            newButton.setTextSize(56);
            grid.addView(newButton);
        }
    }

    /**
     * Start the game with different levels
     *
     * @param view the button clicked
     */
    public void startLevel(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(IntentExtras.gameEnum, game);
        int num = Integer.parseInt((String) ((Button) view).getText()) - 1;
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
