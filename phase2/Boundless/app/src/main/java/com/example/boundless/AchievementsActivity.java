package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boundless.stats.Achievements;

public class AchievementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
    }

    public void backToStats(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void showPixelDescription1(View view) {
        displayToast(getString(R.string.pixel_message1));
    }

    public void showPixelDescription2(View view) {
        displayToast(getString(R.string.pixel_message2));
    }

    public void showPixelDescription3(View view) {
        displayToast(getString(R.string.pixel_message3));
    }

    public void showRotateDescription1(View view) {
        displayToast(getString(R.string.rotate_message1));
    }

    public void showRotateDescription2(View view) {
        displayToast(getString(R.string.rotate_message2));
    }

    public void showRotateDescription3(View view) {
        displayToast(getString(R.string.rotate_message3));
    }

    public void showGpaDescription1(View view) {
        displayToast(getString(R.string.gpa_message1));
    }

    public void showGpaDescription2(View view) {
        displayToast(getString(R.string.gpa_message2));
    }

    public void showGpaDescription3(View view) {
        displayToast(getString(R.string.gpa_message3));
    }

}