package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.boundless.games.BusinessContext;
import com.example.boundless.games.Game;
import com.example.boundless.games.GamesEnum;

public class PauseMenuActivity extends Activity {

    static GamesEnum currentGame;
    static int levelNumber ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause_menu);
        currentGame  = (GamesEnum) getIntent().getSerializableExtra(IntentExtras.gameEnum);
        levelNumber = (int) getIntent().getSerializableExtra(IntentExtras.levelNumber);
    }

    public void resume(View view){
        finish();
        //onBackPressed();
//        Intent intent = new Intent(this, GameActivity.class);
//        currentGame  = (GamesEnum) getIntent().getSerializableExtra(IntentExtras.gameEnum);
//        intent.putExtra(IntentExtras.gameEnum,currentGame);
//        if (BusinessContext.needsLevels(currentGame)) {
//            levelNumber = (int) getIntent().getSerializableExtra(IntentExtras.levelNumber);
//            intent.putExtra(IntentExtras.levelNumber,levelNumber);
//        }
//        startActivity(intent);
    }

    public void backToMain(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void backToLevels(View view){
        Intent intent = new Intent(this, LevelActivity.class);
        intent.putExtra(IntentExtras.gameEnum,currentGame);
        intent.putExtra(IntentExtras.levelNumber, levelNumber);
        startActivity(intent);
    }
    //TODO override onBackPressed
}
