package com.example.boundless.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.boundless.R;
import com.example.boundless.stats.Achievements;
import com.example.boundless.utilities.HandleCustomization;

import java.util.HashMap;
import java.util.Map;

/**
 * The AchievementsActivity handles achievements and displays if they've been completed
 */
public class AchievementsActivity extends AppCompatActivity {
    private HashMap<Integer, Integer> descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        descriptions = new HashMap<>();
        HashMap<Integer, Boolean> achievementStatus = new HashMap<>();
        setContentView(R.layout.activity_achievements);
        HandleCustomization.setActivityBackground(this, getWindow());

        achievementStatus.put(R.id.pixel_cross1, Achievements.isNumPixelTapsFiftyCompleted());
        achievementStatus.put(R.id.pixel_cross2, Achievements.isNumPixelTapsTwoHundredCompleted());
        achievementStatus.put(R.id.pixel_cross3, Achievements.isTwentySecondsOrLessPixel());
        achievementStatus.put(R.id.rotate_cross1,Achievements.isNumRotateTapsFiftyCompleted());
        achievementStatus.put(R.id.rotate_cross2,Achievements.isNumRotateTapsTwoHundredCompleted());
        achievementStatus.put(R.id.rotate_cross3,Achievements.isTwentySecondsOrLessRotate());
        achievementStatus.put(R.id.gpa_cross1, Achievements.isMaxGpaAchieve());
        achievementStatus.put(R.id.gpa_cross2, Achievements.isFailGpaAchieve());
        achievementStatus.put(R.id.gpa_cross3, Achievements.isThreeGpaAchieve());

        for (Map.Entry<Integer, Boolean> entry : achievementStatus.entrySet()) {

            Integer key = entry.getKey();
            Boolean value = entry.getValue();

            if (value) {
                ImageView cross = findViewById(key);
                cross.setImageResource(R.drawable.checkmark);
            }
        }

        descriptions.put(R.id.pixel_achieve1, R.string.pixel_message1);
        descriptions.put(R.id.pixel_achieve2, R.string.pixel_message2);
        descriptions.put(R.id.pixel_achieve3, R.string.pixel_message3);
        descriptions.put(R.id.rotate_achieve1, R.string.rotate_message1);
        descriptions.put(R.id.rotate_achieve2, R.string.rotate_message2);
        descriptions.put(R.id.rotate_achieve3, R.string.rotate_message3);
        descriptions.put(R.id.gpa_achieve1, R.string.gpa_message1);
        descriptions.put(R.id.gpa_achieve2, R.string.gpa_message2);
        descriptions.put(R.id.gpa_achieve3, R.string.gpa_message3);
    }

    /**
     * Brings user back to the statistics page
     *
     * @param view The button clicked
     */
    public void backToStats(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

    /**
     * Displays a message temporarily on screen
     *
     * @param message String message
     */
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows description of the achievement clicked
     *
     * @param view The button clicked
     */
    public void showDescription(View view) {
        if (descriptions.get(view.getId()) == null) return;
        showToast(getString(descriptions.get(view.getId())));
    }
}