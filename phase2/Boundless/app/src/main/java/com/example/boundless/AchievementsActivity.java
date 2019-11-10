package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.boundless.stats.Achievements;

public class AchievementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        TextView achieve = (TextView) findViewById(R.id.achieve);
        achieve.setText(Achievements.printAchievements());
    }

    public void backToStats(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

}