package com.example.boundless;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.boundless.games.BusinessContext;
import com.example.boundless.games.GamesEnum;
import com.example.boundless.users.UserAccount;
import com.example.boundless.utilities.HandleCustomization;
import com.example.boundless.utilities.Session;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The main menu, controls log in and starting games
 */
public class MenuActivity extends AppCompatActivity {
    //TODO: get current user from login activity
    /**
     * The current user, null if not logged in
     */
    UserAccount user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        HandleCustomization.setActivityBackground(this, getWindow());
    }

    /**
     * Start the chosen game.
     *
     * @param view The chosen button that was clicked
     */
    public void startGame(View view) {
        Intent intent;
        GamesEnum gameToPlay = getGame(view);

        if (BusinessContext.needsLevels(gameToPlay))
            intent = new Intent(this, LevelActivity.class);
        else
            intent = new Intent(this, GameActivity.class);

        intent.putExtra(IntentExtras.gameEnum, gameToPlay);
        startActivity(intent);
    }

    private GamesEnum getGame(View view){
        switch (view.getId()) {
            case R.id.PixelGame:
                return GamesEnum.PIXELS;
            case R.id.RotateTile:
                return GamesEnum.ROTATETILE;
            default:
                return GamesEnum.GPACATCHER;
        }
    }

    /**
     * Signs the user out
     * @param view The button clicked
     */
    public void signOut(View view) {
        signOut();
    }

    private void signOut(){
        Session.clearUser();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to the customization page
     * @param view The button clicked
     */
    public void goCustomization(View view){
        Intent intent = new Intent(this, CustomizationActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to the statistics page
     * @param view The button clicked
     */
    public void goStatistics(View view){
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

    /**
     * Override hardware back button to log out of
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to sign out?").setCancelable(false)
                .setPositiveButton("Sign out", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        signOut();
                    }
                })
                .setNegativeButton("Stay signed in", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create().show();
    }

    public void goShop(View view) {
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }
}
