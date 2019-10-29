package com.example.boundless.RotateTileGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

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
            //Statistics.sumTotalScore();
            Log.i(this.toString(), "Made it this far");
            //change stage
            try {userChoice = manager.getTileStage();}
            catch(ArrayIndexOutOfBoundsException e){
                Log.i("RotateTileGame", "all stages have been cleared");
                return true;
            }
            return false;
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
        Log.i(this.toString(),userChoice.toString());
        for (int i = 0; i < userChoice.length; i++) {
            for (int j = 0; j < userChoice.length; j++) {
                canvas.drawBitmap(userChoice[i][j].rotatedImage,
                        manager.getStartX() + j * manager.getTileSize(),
                        manager.getStartY() + manager.getTileSize() * i, paint);
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
        int i = (y - manager.getStartY())/manager.getTileSize();
        int j = (x - manager.getStartX())/manager.getTileSize();
        if (i < manager.getGridSize() && j < manager.getGridSize())
            userChoice[i][j].rotateTile();
    }
}
