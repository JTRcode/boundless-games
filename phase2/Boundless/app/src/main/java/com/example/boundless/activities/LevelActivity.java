package com.example.boundless.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.boundless.R;
import com.example.boundless.games.BusinessContext;
import com.example.boundless.games.GamesEnum;
import com.example.boundless.users.UserAccountManager;
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
        View.OnClickListener listener = this::startLevel;

        for (int i = 0; i < BusinessContext.getNumOfLevels(game); i++) {
            Button newButton = new Button(this);
            newButton.setTypeface(font);
            newButton.setOnClickListener(listener);
            newButton.setPaddingRelative(0, 0, 10, 0);
            newButton.setTextColor(Color.WHITE);
            newButton.setTextSize(56);
            grid.addView(newButton);
            if (UserAccountManager.currentUser.getUnlocked(game) >= i) {
                newButton.setBackgroundResource(R.drawable.level_button);
                newButton.setText(String.valueOf(i + 1));
            } else {
                newButton.setBackgroundResource(R.drawable.level_button_locked);
            }
        }
    }

    /**
     * Start the game with different levels
     *
     * @param view the button clicked
     */
    public void startLevel(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        int num;
        if (view.getId() == R.id.directions) {
            num = Integer.MAX_VALUE;
            intent.putExtra(IntentExtras.gameEnum, BusinessContext.getInstructionsEnum(game));
        } else {
            intent.putExtra(IntentExtras.gameEnum, game);
            try {
                num = Integer.parseInt((String) ((Button) view).getText()) - 1;
            } catch (NullPointerException | NumberFormatException e) {
                Log.d("LevelActivity", "You do not have access to this level yet!");
                return;
            }
        }

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
