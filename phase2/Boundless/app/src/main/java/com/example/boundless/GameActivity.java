package com.example.boundless;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.boundless.games.BusinessContext;
import com.example.boundless.games.GamesEnum;
import com.example.boundless.games.Game;
import com.example.boundless.shop.InventoryItem;
import com.example.boundless.shop.ShopInventory;
import com.example.boundless.users.UserAccountManager;
import com.example.boundless.utilities.HandleCustomization;

import java.util.List;
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
        Log.d("GameActivity", "oh no it's creating again, why don't you just resume, ugh");
        if (savedInstanceState == null)
            Log.d("GameActivity", "no saved instance state");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("GameActivity", "onStart is running  before resume");
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
        Log.d("GameActivity", "setting current game");
        if (currentGame != null) {
            Log.d("GameActivity", "current game is not null");
            setContentView(R.layout.game_page);
            panel = findViewById(R.id.panel);
            panel.chooseGame(currentGame, level);
            Log.d("GameActivity", "Changing to currentGame: " + currentGame);
        } else {
            Log.e("GameActivity", "An error occurred trying to get the currentGame chosen.");
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
        Game currentGame = panel.getGame();
        currentGame.addObserver(this);
        if (level < 1)
            currentGame.showInstructions();
    }

    private void setHintButtons(final GamesEnum game) {
        LinearLayout pauseLayout = findViewById(R.id.inventory_layout);
        List<InventoryItem> inventory = ShopInventory.getInventoryForGame(this, game);
        for (final InventoryItem item : inventory) {
            Button inventoryButton = new Button(this);
            inventoryButton.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
            inventoryButton.setBackgroundResource(item.getImageId());
            inventoryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.useItem();
                }
            });
            inventoryButton.setTag(item.getImageId());
            pauseLayout.addView(inventoryButton);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
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
            runOnUiThread(new Runnable() {
                public void run() {
                    showOverlay((String) o, panel.isGameOver());
                }
            });
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
