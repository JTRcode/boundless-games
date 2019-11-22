package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.boundless.stats.Achievements;

/**
 * The AchievementsActivity handles achievements and displays if they've been completed
 */
public class AchievementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        ImageView pixel_cross1 = (ImageView) findViewById(R.id.pixel_cross1);
        ImageView pixel_cross2 = (ImageView) findViewById(R.id.pixel_cross2);
        ImageView pixel_cross3 = (ImageView) findViewById(R.id.pixel_cross3);

        ImageView rotate_cross1 = (ImageView) findViewById(R.id.rotate_cross1);
        ImageView rotate_cross2 = (ImageView) findViewById(R.id.rotate_cross2);
        ImageView rotate_cross3 = (ImageView) findViewById(R.id.rotate_cross3);

        ImageView gpa_cross1 = (ImageView) findViewById(R.id.gpa_cross1);
        ImageView gpa_cross2 = (ImageView) findViewById(R.id.gpa_cross2);
        ImageView gpa_cross3 = (ImageView) findViewById(R.id.gpa_cross3);


        if (Achievements.numPixelTaps >= 50) {
            pixel_cross1.setImageResource(R.drawable.checkmark);
        }

        if (Achievements.numPixelTaps >= 200) {
            pixel_cross2.setImageResource(R.drawable.checkmark);
        }

        if (Achievements.twentySecondsOrLessPixel) {
            pixel_cross3.setImageResource(R.drawable.checkmark);
        }

        if (Achievements.numRotateTaps >= 50) {
            rotate_cross1.setImageResource(R.drawable.checkmark);
        }

        if (Achievements.numRotateTaps >= 200) {
            rotate_cross2.setImageResource(R.drawable.checkmark);
        }

        if (Achievements.twentySecondsOrLessRotate) {
            rotate_cross3.setImageResource(R.drawable.checkmark);
        }

        if (Achievements.maxGpaAchieve) {
            gpa_cross1.setImageResource(R.drawable.checkmark);
        }

        if (Achievements.threeGpaAchieve) {
            gpa_cross2.setImageResource(R.drawable.checkmark);
        }

        if (Achievements.failGpaAchieve) {
            gpa_cross3.setImageResource(R.drawable.checkmark);
        }
    }

    /**
     * Brings user back to the statistics page
     *
     * @param view The button clicked
     */
    public void backToStats(View view) {
        Intent intent = new Intent(this,StatisticsActivity.class);
        startActivity(intent);
    }

    /**
     * Displays a message temporarily on screen
     *
     * @param message String message
     */
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows description of the achievement clicked
     *
     * @param view The button clicked
     */
    public void showPixelDescription1(View view) {
        displayToast(getString(R.string.pixel_message1));
    }

    /**
     * Shows description of the achievement clicked
     *
     * @param view The button clicked
     */
    public void showPixelDescription2(View view) {
        displayToast(getString(R.string.pixel_message2));
    }

    /**
     * Shows description of the achievement clicked
     *
     * @param view The button clicked
     */
    public void showPixelDescription3(View view) {
        displayToast(getString(R.string.pixel_message3));
    }

    /**
     * Shows description of the achievement clicked
     *
     * @param view The button clicked
     */
    public void showRotateDescription1(View view) {
        displayToast(getString(R.string.rotate_message1));
    }

    /**
     * Shows description of the achievement clicked
     *
     * @param view The button clicked
     */
    public void showRotateDescription2(View view) {
        displayToast(getString(R.string.rotate_message2));
    }

    /**
     * Shows description of the achievement clicked
     *
     * @param view The button clicked
     */
    public void showRotateDescription3(View view) {
        displayToast(getString(R.string.rotate_message3));
    }

    /**
     * Shows description of the achievement clicked
     *
     * @param view The button clicked
     */
    public void showGpaDescription1(View view) {
        displayToast(getString(R.string.gpa_message1));
    }

    /**
     * Shows description of the achievement clicked
     *
     * @param view The button clicked
     */
    public void showGpaDescription2(View view) {
        displayToast(getString(R.string.gpa_message2));
    }

    /**
     * Shows description of the achievement clicked
     *
     * @param view The button clicked
     */
    public void showGpaDescription3(View view) {
        displayToast(getString(R.string.gpa_message3));
    }

}