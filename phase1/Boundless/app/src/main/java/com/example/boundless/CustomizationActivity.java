package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * The CustomizationActivity handles customizations for users, for example,
 * set background music and background picture.
 */
public class CustomizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customiztion);
    }

    /**
     * set music
     *
     * @param view the button clicked
     */
    public void setMusic(View view) {
        switch (view.getId()) {
            case R.id.music_1:
                Session.setMusic(R.raw.minnutesican);
                break;
            case R.id.music_2:
                Session.setMusic(R.raw.novoamorweather);
                break;
            case R.id.music_3:
                Session.setMusic(R.raw.sufjanstevensdeathwithdignity);
                break;
            default:
                break;
        }
        showToast("Music choice saved!");
    }

    /**
     * set the background picture
     *
     * @param view the button that the user click
     */
    public void setBackground(View view) {
        switch (view.getId()) {
            case R.id.background_1:
                Session.setBackground(R.drawable.backgroundone);
                break;
            case R.id.background_2:
                Session.setBackground(R.drawable.backgroundtwo);
                break;
            case R.id.background_3:
                Session.setBackground(R.drawable.backgroundfive);
                break;
            default:
                break;
        }
        showToast("Background saved!");
    }

    /**
     * Brings user back to the main menu
     *
     * @param view The button clicked
     */
    public void backToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void showToast(String message){
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    /**
     * change the theme, for now it is the halloween theme
     * @param view the button of spooks
     */

    public void themeChanged(View view){
        boolean themeOn = Session.getTheme();
        if(!themeOn){
            Session.setBackground(R.drawable.halloween_background);
            Session.setMusic(R.raw.thriller);
            Session.setTheme(true);
        }
        else{
            Session.setBackground(R.drawable.backgroundone);
            Session.setMusic(R.raw.minnutesican);
            Session.setTheme(false);
        }

    }
}
