package com.example.boundless.games;

import com.example.boundless.Panel;
import com.example.boundless.utilities.DrawUtility;
import com.example.boundless.games.rotate_tile_game.TileEnum;
import com.example.boundless.games.rotate_tile_game.TileManager;
import com.example.boundless.games.rotate_tile_game.tiles.Rotation;
import com.example.boundless.games.rotate_tile_game.tiles.Tile;
import com.example.boundless.games.rotate_tile_game.tiles.TileFactory;

/**
 * A game where you rotate tiles to get from point A to point B.
 */
public class RotateTileGame extends Game {

    private TileManager manager = new TileManager();
    private Tile[][] userChoice;
    private int startX;
    private int startY;
    private int tileSize;
    private Tile startEndPipe;
    private int currentLevel;

    public RotateTileGame(int level) {
        currentLevel = level;
        setStartX(100);
        setStartY(Panel.SCREEN_HEIGHT / 4);
        loadNextChoice();
        createStartEndTile();
    }

    private void loadNextChoice() {
        userChoice = manager.getTileStage(currentLevel);
        this.tileSize = (Panel.SCREEN_WIDTH - 2 * this.startX) / userChoice.length;
        resizeUserChoice();
    }

    private void createStartEndTile() {
        startEndPipe = TileFactory.createTile(TileEnum.I);
        startEndPipe.resize(tileSize);
        startEndPipe.setTile(Rotation.EAST);

    }

    public void setStartX(int newX) {
        this.startX = newX;
    }

    public void setStartY(int newY) {
        this.startY = newY;
    }

    private void resizeUserChoice() {
        /*
        Tile[][] resetedSize;
        for (Tile[] tileArray : userChoice)
            for (Tile tile : tileArray)
                tile.resize(this.tileSize);
            */
        for (int i = 0; i < userChoice.length; i++)
            for (int j = 0; j < userChoice.length; j++)
                userChoice[i][j].resize(this.tileSize);
    }


    @Override
    boolean gameOver() {
        return manager.gameOver(userChoice);
    }

    @Override
    public void draw() {
        DrawUtility.drawBitmap(this.startEndPipe.rotatedImage, this.startX - this.tileSize,
                this.startY);
        DrawUtility.drawBitmap(this.startEndPipe.rotatedImage, this.startX +
                        (manager.getGridSize()) * this.tileSize,
                this.startY + (manager.getGridSize() - 1) * this.tileSize);
        for (int i = 0; i < userChoice.length; i++) {
            for (int j = 0; j < userChoice.length; j++) {
                DrawUtility.drawBitmap(userChoice[i][j].rotatedImage,
                        this.startX + j * this.tileSize,
                        this.startY + this.tileSize * i);
            }
        }
    }

    /**
     * Deal with the screen being touched, and check if the game is now over
     *
     * @param x The x coordinate of the touched location.
     * @param y The y coordinate of the touched location.
     */
    @Override
    public void screenTouched(int x, int y) {
        int i = (y - this.startY) / this.tileSize;
        int j = (x - this.startX) / this.tileSize;
        if (i < manager.getGridSize() && j < manager.getGridSize() && i >= 0 && j >= 0)
            userChoice[i][j].rotateTile();
    }

    @Override
    String getInstructions() {
        return "Rotate the tiles to get water from the upper left hand pipe to the lower right hand pipe.";
    }


    @Override
    String getGameOverText() {
        return "GAME OVER!\nYou just finished the rotate tiles game level #" + (currentLevel + 1) + "!";
    }
}
