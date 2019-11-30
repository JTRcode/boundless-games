package com.example.boundless.games.gpa_catcher_game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.example.boundless.Panel;
import com.example.boundless.R;
import com.example.boundless.games.game_utilities.CatcherGameManager;
import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.game_utilities.IGameDrawer;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObject;
import com.example.boundless.utilities.DrawUtility;

import java.util.List;

public class GPAGameDrawer implements IGameDrawer {
    private GPAGameStatus level;
    private Bitmap heart;
    private Bitmap missingHeart;

    public GPAGameDrawer(CatcherGameManager<GPAGameStatus> manager){
        level = manager.getLevel();
        heart = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.heart);
        missingHeart = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.unfilled_heart);
        //TODO redundant scaling of bitmap, since when we deaw it we pass in the size
        heart = Bitmap.createScaledBitmap(heart, GameResources.HEART_SIZE, GameResources.HEART_SIZE, true);
        missingHeart = Bitmap.createScaledBitmap(missingHeart, GameResources.HEART_SIZE, GameResources.HEART_SIZE, true);
//        GpaShop.useItems(); //todo useItems() doesn't work somehow???
    }

    @Override
    public void draw() {
        drawTimeBar();
        drawGPA();
        drawLives();
        drawBasket();
        drawFallingObjects();
    }

    private void drawBasket(){
        Basket basket = level.getBasket();
        DrawUtility.drawBitmap(basket.getAppearance(),basket.getCoordX(),basket.getCoordY());
    }

    /**
     * Draws the object falling
     */
    private void drawFallingObjects() {
        List<FallingObject> objectList = level.getAllFallingObjects();
        for (FallingObject object : objectList){
            DrawUtility.drawBitmap(object.getAppearance(), object.getCoordX(), object.getCoordY());
        }
    }

    private void drawTimeBar() {
        int color;
        double maxTime = GameResources.GPAGAME_MAX_TIME;
        if (level.getTime() >= maxTime / 2) color = Color.GREEN;
        else if (level.getTime() >= maxTime  / 4) color = Color.YELLOW;
        else color = Color.RED;
        //TODO should not be casting Time. NEED TO rework entire TIme mechanic
        int length = (int) Math.round((level.getTime()/GameResources.GPAGAME_MAX_TIME) * Panel.SCREEN_WIDTH);
        DrawUtility.drawRectangle(new int[]{0, 10, length, 70}, color);
    }

    private void drawGPA() {
        double roundedGPA = Math.round(level.getGpa() * 100) / 100.0;
        DrawUtility.drawString("GPA: " + roundedGPA, 50, 125, Color.WHITE, 60);
    }

    private void drawLives() {
        for (int missLives = 1; missLives <= level.getMaxLives(); missLives++){
            DrawUtility.drawBitmap(missingHeart, Panel.SCREEN_WIDTH - missLives * GameResources.HEART_SIZE, 140);
        }
        for (int lives = 1; lives <= level.getLives(); lives++) {
            DrawUtility.drawBitmap(heart, Panel.SCREEN_WIDTH - lives * GameResources.HEART_SIZE, 140);
        }
    }


}
