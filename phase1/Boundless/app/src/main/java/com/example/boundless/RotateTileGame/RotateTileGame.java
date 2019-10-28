package com.example.boundless.RotateTileGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.boundless.Game;
import com.example.boundless.Statistics;

/**
 * A game where you rotate tiles to get from point A to point B.
 */
public class RotateTileGame extends Game {

    private TileManager manager = new TileManager(5);
    private Tile[][] userChoice;
    private boolean gameFinished = false;
    /**
     * The width of each grid square.
     */
    private Paint paint;

    public RotateTileGame(Context context) {
        super(context);
        manager.setUpTiles();
        userChoice = manager.getTileStage();
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
    }

    @Override
    public boolean gameOver() {
        //TODO need to check the game is completed correctly
        gameFinished = manager.gameOver(userChoice);
        if (gameFinished){
            showToast("Correct!");
            //change stage
            Statistics.sumTotalScore();
            return true;
        } else{
            showToast("Incorrect!");
            return false;
        }
    }

    @Override
    public boolean isGameFinished() {
        return gameFinished;
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < manager.getGridSize(); i++) {
            for (int j = 0; j < manager.getGridSize(); j++) {
                canvas.drawBitmap(userChoice[i][j].rotatedImage,
                        manager.getStartX() + j * manager.getTileSize(),
                        manager.getStartY() + manager.getTileSize() * i, paint);
            }
        }
    }

    /**
     * Draw text on the canvas.
     *
     * @param canvas The canvas to draw on.
     * @param text   The text to draw.
     * @param x      The x location to draw text at.
     * @param y      The y location to draw text at.
     */
    private void drawString(Canvas canvas, String text, int x, int y) {
        canvas.drawText(text, x, y, paint);
    }

    /**
     * Deal with the screen being touched, and check if the game is now over
     *
     * @param x The x coordinate of the touched location.
     * @param y The y coordinate of the touched location.
     * @return whether the game should be over.
     */
    @Override
    public void screenTouched(int x, int y) {
        int i = (y - manager.getStartY())/manager.getTileSize();
        int j = (x - manager.getStartX())/manager.getTileSize();
        if (i < manager.getGridSize() && j < manager.getGridSize())
            userChoice[i][j].rotateTile();
    }
}
