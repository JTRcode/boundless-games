package com.example.boundless.games.gpa_catcher_game.catchers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.boundless.Panel;
import com.example.boundless.R;
import com.example.boundless.games.game_utilities.GameResources;

/**
 * A basket for catching falling objects
 */
public class Basket extends Catcher {

    /**
     * A new basket to catch items in
     */
    public Basket() {
        setAppearance(BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.basket));
        int size = GameResources.GPAGAME_BASKET_SIZE;
        setAppearance(Bitmap.createScaledBitmap(getAppearance(), size, size, true));
    }

    /**
     * Moves left on the screen
     */
    @Override
    public void moveLeft(){
        setCoordX(getCoordX()-getSpeed());
    }

    /**
     * Moves right on the screen
     */
    @Override
    public void moveRight(){
        setCoordX(getCoordX()+getSpeed());
    }

}
