package com.example.boundless.RotateTileGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.boundless.Game;
import com.example.boundless.Panel;

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
        gameFinished = true;
        return manager.gameOver(userChoice);
    }

    @Override
    public boolean isGameFinished() {
        return gameFinished;
    }


    /**
     * Rotates a given tile clockwise.
     * @param i The first coordinate of the tile to rotate
     * @param j The second coordinate of the tile to rotate
     */
    public void rotate(int i, int j) {
        userChoice[i][j].rotateTile();
    }

    @Override
    public void draw(Canvas canvas) {
        //drawBitmap(Bitmap bitmap, float left, float top, Paint paint)
        //TODO: Jackson finish this up
        for (int i = 0; i < manager.getGridSize(); i++) {
            for (int j = 0; j < manager.getGridSize(); j++) {
                canvas.drawBitmap(userChoice[i][j].image, manager.getStartX() + j * manager.getTileSize(), manager.getStartY() + manager.getTileSize() * i, paint);
            }
        }
        addGameOverButton(canvas);
    }

    /**
     * Adds the game over button to the screen.
     * @param canvas
     */
    private void addGameOverButton(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
        drawString(canvas, "Check your answer!", manager.getStartX(), manager.getStartY() + manager.getTileSize() * manager.getGridSize() + 300);
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
        //TODO: Update location touched with rotated tile (see PixelGame.convertCoordToIJ method for example on getting the [i] and [j] coordinates)
        int i = (y - manager.getStartY())/manager.getTileSize();
        int j = (x - manager.getStartX())/manager.getTileSize();

        if ((Math.abs(x - manager.getStartX()) < 300 && Math.abs(y - (manager.getStartY() +manager.getTileSize() * manager.getGridSize() + 300)) < 100)){
            System.out.println("NYAHHHH stoppp it plaz");
            gameOver();
        }else {
            try {
                userChoice[i][j].rotateTile();
            } catch (Exception e) {
            }
        }
    }
}
