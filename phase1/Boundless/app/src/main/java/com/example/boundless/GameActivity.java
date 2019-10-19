package com.example.boundless;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Bundle extras = getIntent().getExtras();
        String gameNum = extras.getString("GAMENAME");
        System.out.println("Name: "+gameNum);
        switch (gameNum){
            case "GPA Catcher":
                setContentView(new Panel(this, GamesEnum.GPACATCHER));
                break;
            case "Pixel":
                setContentView(new Panel(this, GamesEnum.PIXELS));
                break;
            case "Rotate Tile":
                setContentView(new Panel(this, GamesEnum.ROTATETILE));
                break;
            default:
                break;
        }
    }


}
