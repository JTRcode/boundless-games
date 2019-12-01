package com.example.boundless.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.boundless.Panel;
import com.example.boundless.R;
import com.example.boundless.games.BusinessContext;
import com.example.boundless.games.GamesEnum;
import com.example.boundless.games.Game;
import com.example.boundless.shop.InventoryItem;
import com.example.boundless.shop.ShopInventory;
import com.example.boundless.users.UserAccountManager;
import com.example.boundless.utilities.HandleCustomization;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * The activity that holds the game itself
 */
public class GameActivity extends Activity implements Observer {
    private Panel panel;
    private GamesEnum currentGame;
    private int level = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            Log.d("GameActivity", "no saved instance state");
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupGame();
    }

    private void setupGame() {
        setCurrentGame();
        setHintButtons(currentGame);
        HandleCustomization.setGameBackground(this, findViewById(R.id.ConstraintLayout));
        HandleCustomization.startMusic(this);
    }

    private void setCurrentGame() {
        currentGame = (GamesEnum) getIntent().getSerializableExtra(IntentExtras.gameEnum);
        if (BusinessContext.needsLevels(currentGame))
            level = (int) getIntent().getSerializableExtra(IntentExtras.levelNumber);
        if (currentGame != null) {
            Log.d("GameActivity", "Changing to currentGame: " + currentGame);
            setContentView(R.layout.game_page);
            panel = findViewById(R.id.panel);
            panel.chooseGame(currentGame, level);
        } else {
            Log.e("GameActivity", "An error occurred trying to get the currentGame chosen.");
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
        Game gameInstance = panel.getGame();
        gameInstance.addObserver(this);
        if (level < 1)
            gameInstance.showInstructions();
    }

    private void setHintButtons(final GamesEnum game) {
        LinearLayout pauseLayout = findViewById(R.id.inventory_layout);
        pauseLayout.removeAllViews();
        Map<InventoryItem, Integer> inventory = ShopInventory.getInventoryForGame(this, game);
        for (final InventoryItem item : inventory.keySet()) {
            for (int i = 0; i < inventory.get(item); i++) {
                Button inventoryButton = new Button(this);
                inventoryButton.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
                inventoryButton.setBackgroundResource(item.getImageId());
                inventoryButton.setPaddingRelative(10, 0, 10, 0);
                inventoryButton.setOnClickListener(view -> {
                    item.useItem(this);
                    setHintButtons(game);
                });
                inventoryButton.setTag(item.getImageId());
                pauseLayout.addView(inventoryButton);
            }
        }
    }

    /**
     * Deals with the pause button on the screen being pressed
     *
     * @param view The button pressed
     */
    public void pauseButtonPressed(View view) {
        panel.pause();
        findViewById(R.id.pauseLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.pauseLayout).bringToFront();
        HandleCustomization.pauseMusic(this);
    }

    /**
     * Deals with the resume button being pressed
     *
     * @param view The button pressed
     */
    public void resumePressed(View view) {
        findViewById(R.id.pauseLayout).setVisibility(View.INVISIBLE);
        HandleCustomization.startMusic(this);
        panel.resume();
        Log.d("GameActivity", "onResume()!!!");
    }

    private void showOverlay(String text, final boolean gameIsOver) {
        panel.pause();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text).setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (gameIsOver) onBackPressed();
                        else panel.resume();
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onBackPressed();
                    }
                });
        builder.create().show();
    }

    @Override
    public void update(Observable observable, final Object o) {
        if (panel.isGameOver() && UserAccountManager.currentUser.getUnlocked(currentGame) == level)
            UserAccountManager.currentUser.addUnlocked(currentGame);
        try {
            runOnUiThread(() -> showOverlay((String) o, panel.isGameOver()));
        } catch (ClassCastException e) {
            Log.e("GameActivity", "The observer needs a string to show an alert!");
        }
    }

    /**
     * Override hardware back button to go to main activity or level activity
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MenuActivity.class);
        if (BusinessContext.needsLevels(currentGame)) {
            intent = new Intent(this, LevelActivity.class);
            if (BusinessContext.isInstructions(currentGame))
                intent.putExtra(IntentExtras.gameEnum, BusinessContext.getRegularLevel(currentGame));
            else intent.putExtra(IntentExtras.gameEnum, currentGame);
        }
        HandleCustomization.pauseMusic(this);
        startActivity(intent);
    }

    /**
     * Goes back to the main menu
     *
     * @param view The button clicked
     */
    public void backToMain(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Goes back to the level screen
     *
     * @param view The button clicked
     */
    public void backToLevels(View view) {
        Intent intent = new Intent(this, LevelActivity.class);
        intent.putExtra(IntentExtras.gameEnum, currentGame);
        intent.putExtra(IntentExtras.levelNumber, level);
        startActivity(intent);
    }
}
