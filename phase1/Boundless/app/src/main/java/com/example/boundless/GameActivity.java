package com.example.boundless;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.boundless.GPACatcherGame.GPAPanel;
import com.example.boundless.PixelGame.PixelPanel;

public class GameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Bundle extras = getIntent().getExtras();
        String gameNum = extras.getString("GAMENAME");
        switch (gameNum){
            case "GPA Catcher":
                setContentView(new GPAPanel(this));
                break;
            default:
                break;
        }
    }


}
