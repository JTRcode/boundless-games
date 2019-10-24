package com.example.boundless.RotateTileGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.boundless.Game;

/**
 * A game where you rotate tiles to get from point A to point B.
 */
public class RotateTileGame extends Game {

    private TileManager manager = new TileManager(5);
    private Tile[][] userChoice;
    /**
     * The width of each grid square.
     */
    private Paint paint;

    public RotateTileGame() {
        manager.setUpTiles();
        userChoice = manager.getTileStage();
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
    }

    @Override
    public boolean gameOver() {
        return manager.gameOver(userChoice);
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
        Tile tile = manager.createNewTile(TileEnum.I);
        for (int i = 0; i < manager.getGridSize(); i++) {
            for (int j = 0; j < manager.getGridSize(); j++) {
                canvas.drawBitmap(userChoice[i][j].image, manager.getStartX() + j * manager.getTileSize(), manager.getStartY() + manager.getTileSize() * i, paint);
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
        //TODO: Update location touched with rotated tile (see PixelGame.convertCoordToIJ method for example on getting the [i] and [j] coordinates)
        userChoice[0][0].rotateTile();
    }
}
