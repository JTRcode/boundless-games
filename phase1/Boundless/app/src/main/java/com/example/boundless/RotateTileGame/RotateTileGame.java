package com.example.boundless.RotateTileGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.boundless.Game;

/**
 * A game where you rotate tiles to get from point A to point B.
 */
public class RotateTileGame extends Game {

    private TileManager manager = new TileManager();
    private Tile[][] userChoice;

    private Paint paint;

    public RotateTileGame(){
        manager.setUpTiles();
        userChoice = manager.getTileStage();
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(100);
    }
    @Override
    public boolean gameOver() {
        return manager.gameOver(userChoice);
        //TODO
    }


    /**
     * Rotates a given tile clockwise.
     */
    public void rotate(int x, int y) {
        userChoice[x][y].rotateTile();
    }

    @Override
    public void draw(Canvas canvas) {
        //drawBitmap(Bitmap bitmap, float left, float top, Paint paint)
        //TODO
        Tile tile = manager.createNewTile(TileEnum.I);
        canvas.drawText("Jackson", 100,100,paint);
        canvas.drawBitmap(tile.image, 100, 100, paint);
        for (int x = 0; x < manager.getGridSize(); x++){
            for (int y = 0; y < manager.getGridSize(); y++){
                canvas.drawBitmap(userChoice[x][y].image, 100*y, 100*x, paint);
            }
        }
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
        //TODO Update purposes
    }

    //TODO
}
