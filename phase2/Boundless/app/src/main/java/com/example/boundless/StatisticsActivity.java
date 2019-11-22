package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.boundless.stats.Statistics;
import com.example.boundless.utilities.HandleCustomization;

/**
 * The StatisticsActivity handles statistics displays their values for the user.
 */
public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statistics);

        TextView stats = findViewById(R.id.stats);
        stats.setText(Statistics.printStats());


        //TextView time = findViewById(R.id.total_time);
        //time.setText(Statistics.printTime());

        //TextView points = findViewById(R.id.points);
        //points.setText(Statistics.printPoints());

        //TextView taps = findViewById(R.id.taps);
        //taps.setText(Statistics.printTaps());


        HandleCustomization.setActivityBackground(this, getWindow());
    }

    /**
     * Go back to the main menu
     *
     * @param view The button clicked
     */
    public void backToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Go to the achievements page
     *
     * @param view The button clicked
     */
    public void goAchievements(View view){
        Intent intent = new Intent(this, AchievementsActivity.class);
        startActivity(intent);
    }
}
