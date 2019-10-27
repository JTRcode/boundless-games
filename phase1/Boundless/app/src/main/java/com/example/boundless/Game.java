package com.example.boundless;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.Toast;

/**
 * A single game
 */
public abstract class Game {
    private Context context;

    public Game(Context context){
        this.context = context;
    }

    public abstract boolean gameOver();
    public abstract void draw(Canvas canvas);
    public abstract void screenTouched(int x, int y);
    public void update(){}

    protected void showToast(String message){
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    //TODO: update all the javadocs
}
