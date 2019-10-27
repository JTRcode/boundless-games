package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

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
     * @param view the button clicked
     */
    public void setMusic(View view){
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
    }

    /**
     * set the background picture
     * @param view the button that the user click
     */
    public void setBackground(View view){
        switch (view.getId()) {
            case R.id.background_1:
                Session.setBackground(R.drawable.backgroundone);
                break;
            case R.id.background_2:
                Session.setBackground(R.drawable.backgroundtwo);
                break;
            case R.id.background_3:
                Session.setBackground(R.drawable.backgroundfive);
            default:
                break;
        }

    }
}
