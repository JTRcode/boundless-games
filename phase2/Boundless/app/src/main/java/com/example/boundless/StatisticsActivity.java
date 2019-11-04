package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.boundless.stats.Statistics;
import com.example.boundless.utilities.Session;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        TextView stats = (TextView) findViewById(R.id.stats);
        stats.setText(Statistics.printStats());
        if(Session.getTheme()){
            //getWindow().setBackgroundDrawableResource(R.drawable.hallo_menu);
        }
        else{
            getWindow().setBackgroundDrawableResource(R.drawable.stats);
        }
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void goAchievements(View v){
        Intent intent = new Intent(this, AchievementsActivity.class);
        startActivity(intent);
    }
}
