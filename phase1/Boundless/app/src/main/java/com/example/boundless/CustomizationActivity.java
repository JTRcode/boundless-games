package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.boundless.utilities.Session;

/**
 * The CustomizationActivity handles customizations for users, for example,
 * set background music and background picture.
 */
public class CustomizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customiztion);
        if(Session.getTheme()){
            getWindow().setBackgroundDrawableResource(R.drawable.halloween_custom);
        }
        else{
            getWindow().setBackgroundDrawableResource(R.drawable.custom);
        }
    }

    /**
     * set music
     *
     * @param view the button clicked
     */
    public void setMusic(View view) {
        if(Session.getTheme()){
            showToast("Turn the theme off first");
        }
        else {
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
            showToast("Music choice saved");
        }
    }

    /**
     * set the background picture
     *
     * @param view the button that the user click
     */
    public void setBackground(View view) {
        if(Session.getTheme()){
            showToast("Turn the theme off first");
        }
        else {
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
     * Turn the halloween theme on
     * @param view the button clicked
     */
    public void themeOn(View view){
        Session.setBackground(R.drawable.halloween_background);
        Session.setMusic(R.raw.thriller);
        Session.setTheme(true);
        showToast("Spooky Magic happens!!!");
    }

    /**
     * Turn the theme off
     * @param view the button clicked
     */
    public void themeOff(View view){
        Session.setBackground(R.drawable.backgroundone);
        Session.setMusic(R.raw.minnutesican);
        Session.setTheme(false);
        showToast("See you soon :)");
    }
}
